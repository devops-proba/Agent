import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/services/product-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})

export class ProductsComponent implements OnInit {

  public productList:object[] = [];
  public orderItems:Array<any> = [];
  constructor(private productService: ProductServiceService, private toastr: ToastrService, private router: Router, private route: ActivatedRoute,private dataService:DataService) { }

  ngOnInit(): void {
    this.getProducts()
    this.dataService.orderItems.subscribe(orderItems => this.orderItems = orderItems);
    console.log("PRoducts");
    console.log(this.orderItems);
  }

  delete(id: number): void {
    this.productService.removeProduct(id).subscribe(
      () => {
        this.toastr.success('You have successfully removed product');
        this.productList.splice(this.productList.indexOf(this.productList.find(m => m['id'] === id)), 1);
        // window.location.reload();
      },
      error => {
        console.log(error.error)

      }
    )
  }

  addToCart(product:any):void{
    if(product.amount <= 0){
      this.toastr.error("Product quantity must be positive number!");
      return;
    }
    var itemFound = null;
    this.orderItems.forEach(item =>{
      if(item.product.id == product.id){
        itemFound = item;
        return;
      }
    })

    if(itemFound == null){
      if(product.quantity < product.amount){
        this.toastr.error('Not enough product');
        return;
      }
      var item = {
        product: product,
        totalPrice : product.amount * product.price
      }
      this.orderItems.push(item);
    }
    else{
      if(itemFound.product.quantity < itemFound.product.amount+product.amount){
        this.toastr.error('Not enough product');
        return;
      }
      itemFound.product.amount += product.amount;
      itemFound.totalPrice = itemFound.product.amount * itemFound.product.price;
    }
    // console.log(product.quantity);
    // console.log(product.amount);

    this.dataService.changeOrderItems(this.orderItems);
    this.toastr.success("Product added to cart!");
    
  }

  getProducts(): void {
    this.productService.getProducts().subscribe(productList => this.productList = productList);

    // var product = {
    //   id : 1,
    //   name : "P!",
    //   price : 50.5,
    //   quantity : 3,
    //   amount : 0,
    //   picture : "https://www.publicdomainpictures.net/pictures/320000/velka/background-image.png"
    // } 
    // var product1 = {
    //   id : 2,
    //   name : "Paa!",
    //   price : 50.5,
    //   quantity : 3,
    //   amount : 0,
    //   picture : "https://www.publicdomainpictures.net/pictures/320000/velka/background-image.png"
    // } 
    // var product2 = {
    //   id : 3,
    //   name : "P!",
    //   price : 50.5,
    //   quantity : 3,
    //   amount : 0,
    //   picture : "https://www.publicdomainpictures.net/pictures/320000/velka/background-image.png"
    // }
    
    // var product3 = {
    //   id : 4,
    //   name : "P!",
    //   price : 50.5,
    //   quantity : 3,
    //   amount : 0,
    //   picture : "https://www.publicdomainpictures.net/pictures/320000/velka/background-image.png"
    // }
    // this.productList = [product, product1, product2, product3];
  }
}
