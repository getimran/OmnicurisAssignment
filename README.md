# OmnicurisAssignment

## Steps:
1) Pull code to the local system.

2) Use any Java IDE to import the project.

3) Go to application.properties and change MySql credentials for below mentioned keys:
spring.datasource.username
spring.datasource.password

4) Run project as Spring boot app.

### Database (MySql):
2 tables will be created in MySql on applocation startup.
Schema name: omnicuris
Tables: product, orders

### If testing on local: 
base-url: http://localhost:8080

### API testing tool: POSTMAN

# 1.    CRUD operations on items(Product)

### CREATE: 
endpoint: /omnicuris/v1/ecommerce/product/add
method: POST
request body:
{
    "productName": "ORS TetraPack",
    "productDesc": "This is ORS TetraPack 500ml.",
    "price": 25,
    "stockQuantity": 345
}
response body:
{
    "id": 3,
    "productName": "ORS TetraPack",
    "productDesc": "This is ORS TetraPack 500ml.",
    "price": 25,
    "stockQuantity": 345
} 


### RETRIEVE:
endpoint: /omnicuris/v1/ecommerce/product/{id}
method: GET
request body: NONE
response body:
{
    "id": 1,
    "productName": "Green MedPack",
    "productDesc": "This is Green Medi pack.",
    "price": 105,
    "stockQuantity": 23
}

### UPDATE:
endpoint: /omnicuris/v1/ecommerce/product/update/{id}
method: PUT
request body:
{
       "productName": "ORS TetraPack 500ml",
        "productDesc": "This is ORS Tetra Pack 500ml.",
        "price": 78,
        "stockQuantity": 345
}
response body:
{
    "id": 2,
    "productName": "ORS TetraPack 500ml",
    "productDesc": "This is ORS Tetra Pack 500ml.",
    "price": 78,
    "stockQuantity": 345
}


### DELETE:
endpoint: /omnicuris/v1/ecommerce/product/delete/{id}
method: PUT
request body: NONE
response body: Product with id {id} deleted successfully.


IF PRODUCT WITH THE GIVEN ID ISN'T FOUND, THEN:
response body: Product with id {id} not found.

-------------------------------------------------------------------------------------------------------------
# 2.    All items listing (Products)

endpoint: /omnicuris/v1/ecommerce/product/all
method: GET
request body: NONE
response body:
[
    {
        "id": 1,
        "productName": "Green MedPack",
        "productDesc": "This is Green Medi pack.",
        "price": 105,
        "stockQuantity": 23
    },
    {
        "id": 2,
        "productName": "Ayurveda Cream 200ml",
        "productDesc": "This is Ayurveda Cream 200ml",
        "price": 235.89,
        "stockQuantity": 13
    }
]

--------------------------------------------------------------------------------------------------------------

# 3.    Single & bulk ordering (Just consider the item, no. of items & email ids as params for ordering)

endpoint: /omnicuris/v1/ecommerce/order/place/{userEmail}
method: POST
request body: 
SINGLE PRODUCT ORDER REQUEST:
[
{
	"product":{
		"id" : 2
	},
	"quantity": 4
}
]

BULK PRODUCTS ORDER REQUEST:
[
{
	"product":{
		"id" : 2
	},
	"quantity": 4
},
{
	"product":{
		"id" : 1
	},
	"quantity": 2
}	
]

response body:
SUCCESS RESPONSE:
{
    "id": 1,
    "orderTotal": 1153.56,
    "totalItems": 6,
    "orderStatus": "SUCCESS",
    "message": "Order Placed Successfully !!"
}

RESPONSE IN CASE OF OUT OF STOCK:
{
    "id": 0,
    "orderTotal": 0,
    "totalItems": 0,
    "orderStatus": "FAILED",
    "message": "Order Failed. Product with id 2 is Out of Stock."
}

RESPONSE IN CASE OF LOW STOCK:
{
    "id": 0,
    "orderTotal": 0,
    "totalItems": 0,
    "orderStatus": "FAILED",
    "message": "Order Failed. Product with id 2 is in low stock. InStock: 10 OrderedQuantity: 15"
}

-------------------------------------------------------------------------------------------------------------
# 4.    All orders

endpoint: /omnicuris/v1/ecommerce/order/all
method: GET
request body: NONE
response body:
[
    {
        "id": 1,
        "orderTotal": 912,
        "totalItems": 11,
        "orderStatus": "SUCCESS",
        "message": "Order Placed Successfully !!"
    },
    {
        "id": 2,
        "orderTotal": 912,
        "totalItems": 11,
        "orderStatus": "SUCCESS",
        "message": "Order Placed Successfully !!"
    },
    {
        "id": 3,
        "orderTotal": 1332,
        "totalItems": 15,
        "orderStatus": "SUCCESS",
        "message": "Order Placed Successfully !!"
    }
]