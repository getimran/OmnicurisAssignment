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
endpoint: /omnicuris/v1/ecommerce/product/add <br/>
method: POST    <br/>
request body:   <br/>
{               <br/>
    "productName": "ORS TetraPack",  <br/>
    "productDesc": "This is ORS TetraPack 500ml.",  <br/>
    "price": 25,   <br/>
    "stockQuantity": 345   <br/>
}   <br/>
response body:   <br/>
{   <br/>
    "id": 3,   <br/>
    "productName": "ORS TetraPack",    <br/>
    "productDesc": "This is ORS TetraPack 500ml.",   <br/>
    "price": 25,     <br/>
    "stockQuantity": 345   <br/>
} <br/>


### RETRIEVE:
endpoint: /omnicuris/v1/ecommerce/product/{id}     <br/>
method: GET    <br/>
request body: NONE    <br/>
response body:   <br/>
{    <br/>
    "id": 1,   <br/>
    "productName": "Green MedPack",   <br/>
    "productDesc": "This is Green Medi pack.",    <br/>
    "price": 105,    <br/>
    "stockQuantity": 23    <br/>
}

### UPDATE:
endpoint: /omnicuris/v1/ecommerce/product/update/{id}    <br/>
method: PUT    <br/>
request body:    <br/>
{    <br/>
       "productName": "ORS TetraPack 500ml",    <br/>
        "productDesc": "This is ORS Tetra Pack 500ml.",    <br/>
        "price": 78,    <br/>
        "stockQuantity": 345    <br/>
}    <br/>
response body:   <br/>
{    <br/>
    "id": 2,   <br/>
    "productName": "ORS TetraPack 500ml",    <br/>
    "productDesc": "This is ORS Tetra Pack 500ml.",    <br/>
    "price": 78,   <br/>
    "stockQuantity": 345    <br/>
}


### DELETE:
endpoint: /omnicuris/v1/ecommerce/product/delete/{id}   <br/>
method: PUT    <br/>
request body: NONE     <br/>
response body: Product with id {id} deleted successfully.   <br/>


IF PRODUCT WITH THE GIVEN ID ISN'T FOUND, THEN:  <br/>
response body: Product with id {id} not found.  <br/>

-------------------------------------------------------------------------------------------------------------
# 2.    All items listing (Products)

endpoint: /omnicuris/v1/ecommerce/product/all     <br/>
method: GET    <br/>
request body: NONE    <br/>
response body:   <br/>
[    <br/>
    {     <br/>
        "id": 1,   <br/>
        "productName": "Green MedPack",   <br/>
        "productDesc": "This is Green Medi pack.",    <br/>
        "price": 105,    <br/>
        "stockQuantity": 23    <br/>
    },   <br/>
    {   <br/>
        "id": 2,    <br/>
        "productName": "Ayurveda Cream 200ml",    <br/>
        "productDesc": "This is Ayurveda Cream 200ml",    <br/>
        "price": 235.89,    <br/>
        "stockQuantity": 13    <br/>
    }   <br/>
]   <br/>

--------------------------------------------------------------------------------------------------------------

# 3.    Single & bulk ordering (Just consider the item, no. of items & email ids as params for ordering)

endpoint: /omnicuris/v1/ecommerce/order/place/{userEmail}    <br/>
method: POST    <br/>
request body:     <br/>
SINGLE PRODUCT ORDER REQUEST:   <br/>
[   <br/>
{   <br/>
	"product":{    <br/>
		"id" : 2   <br/>
	},   <br/>
	"quantity": 4   <br/>
}   <br/>
]   <br/>

BULK PRODUCTS ORDER REQUEST:   <br/>
[   <br/>
{     <br/>
	"product":{   <br/>
		"id" : 2   <br/>
	},   <br/>
	"quantity": 4   <br/>
},   <br/>
{   <br/>
	"product":{    <br/>
		"id" : 1    <br/>
	},     <br/>
	"quantity": 2     <br/>
}	<br/>
]   <br/>

response body:   <br/>
SUCCESS RESPONSE:   <br/>
{   <br/>
    "id": 1,   <br/>
    "orderTotal": 1153.56,    <br/>
    "totalItems": 6,    <br/>
    "orderStatus": "SUCCESS",   <br/>
    "message": "Order Placed Successfully !!"    <br/>
}

RESPONSE IN CASE OF OUT OF STOCK:    <br/>
{
    "id": 0,    <br/>
    "orderTotal": 0,    <br/>
    "totalItems": 0,    <br/>
    "orderStatus": "FAILED",   <br/>
    "message": "Order Failed. Product with id 2 is Out of Stock."   <br/>
}   <br/>

RESPONSE IN CASE OF LOW STOCK:   <br/>
{   <br/>
    "id": 0,   <br/>
    "orderTotal": 0,   <br/>
    "totalItems": 0,    <br/>
    "orderStatus": "FAILED",   <br/>
    "message": "Order Failed. Product with id 2 is in low stock. InStock: 10 OrderedQuantity: 15"   <br/>
}

-------------------------------------------------------------------------------------------------------------
# 4.    All orders

endpoint: /omnicuris/v1/ecommerce/order/all   <br/>
method: GET   <br/>
request body: NONE   <br/>
response body:   <br/>
[   <br/>
    {   <br/>
        "id": 1,     <br/>
        "orderTotal": 912,     <br/>
        "totalItems": 11,    <br/>
        "orderStatus": "SUCCESS",     <br/>
        "message": "Order Placed Successfully !!"    <br/>
    },   <br/>
    {   <br/>
        "id": 2,    <br/>
        "orderTotal": 912,    <br/>
        "totalItems": 11,     <br/>
        "orderStatus": "SUCCESS",     <br/>
        "message": "Order Placed Successfully !!"   <br/>
    },   <br/>
    {   <br/>
        "id": 3,     <br/>
        "orderTotal": 1332,   <br/>
        "totalItems": 15,   <br/>
        "orderStatus": "SUCCESS",     <br/>
        "message": "Order Placed Successfully !!"    <br/>
    }   <br/>
]  <br/>
