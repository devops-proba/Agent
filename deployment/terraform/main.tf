terraform {
  backend "pg" {
  }
}

provider "heroku" {
}

variable "product_app_name" {
  description = "Unique name of the Product app"
}

variable "gateway_app_name" {
  description = "Unique name of the Gateway app"
}

resource "heroku_config" "prod" {
  vars = {
    STAGE = "PROD"
  }
}

resource "heroku_app" "product_service" {
  name   = var.product_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "product_service" {
  app = heroku_app.product_service.id

  source {
    path = "products"
  }
}

resource "heroku_app_config_association" "product_service" {
  app_id = heroku_app.product_service.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon" "database" {
  app  = heroku_app.product_service.name
  plan = "heroku-postgresql:hobby-dev"
}



resource "heroku_app" "gateway_service" {
  name   = "var.gateway_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "gateway_service" {
  app = heroku_app.gateway_service.id

  source {
    path = "gateway"
  }
}

resource "heroku_app_config_association" "gateway_service" {
  app_id = heroku_app.gateway_service.id

  vars = heroku_config.prod.vars
}

output "product_app_url" {
  value = "https://${heroku_app.product_service.name}.herokuapp.com"
}
output "gateway_app_url" {
  value = "https://${heroku_app.gateway_service.name}.herokuapp.com"
}