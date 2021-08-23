echo "Running deploy.sh"

ALL_HEROKU_APPS=$(heroku apps) && export ALL_HEROKU_APPS

echo $ALL_HEROKU_APPS
echo $TERRAFORM_PG_BACKEND

if [[ $ALL_HEROKU_APPS =~ "$TERRAFORM_PG_BACKEND" ]]; 
then
  echo "It's there!"
else 
  echo "It's no there!"
  heroku create $TERRAFORM_PG_BACKEND
  heroku addons:create heroku-postgresql:hobby-dev --app $TERRAFORM_PG_BACKEND
fi

cd terraform || exit
DATABASE_URL=$(heroku config:get DATABASE_URL --app "$TERRAFORM_PG_BACKEND") && export DATABASE_URL

# prepare Dockerfile-s

rm -rf ./product-service/Dockerfile
rm -rf ./Gateway/Dockerfile


echo "FROM $APP_IMAGE_NAME_AGENT_PRODUCT" >> ./product-service/Dockerfile
cat ./product-service/Dockerfile

echo "FROM $APP_IMAGE_NAME_AGETN_GATEWAY" >> ./Gateway/Dockerfile
cat ./Gateway/Dockerfile

terraform init -backend-config="conn_str=$DATABASE_URL"

echo "Connected to database"

terraform apply -auto-approve -var agent_products_name="$APP_NAME_AGENT_PRODUCTS" \
                              -var gateway_app_name="$APP_NAME_AGENT_GATEWAY" 
