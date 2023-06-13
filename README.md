
# PayTacker

Project Assignment for EP R&D

##### Table of Contents  
- [Task 1: CRUD](#task-1-crud)
  - [Get all payments](#get-all-payments)
  - [Get payment](#get-payment)
  - [Create payment](#create-payment)
  - [Update payment](#update-payment)
  - [Delete payment](#delete-payment)
- [Task 2: Filter and Pagination](#task-2-filter-and-pagination)
  - [Get payments by filter](#get-payments-by-filter)
- [Task 3: Handling Large Page Sizes](#task-3-handling-large-page-sizes)
- [Task 4: Frontend Search Component](#task-4-frontend-search-component)
- [Task 5: Addressing Slow Search Performance](#task-5-addressing-slow-search-performance)
# Task #1: CRUD
### API Endpoints

#### Get all payments

```http
  GET /api/payment
```

| Parameter | Type     | Required     | Description                |
| :-------- | :------- | :------- | :------------------------- |
| `page` | `int` | No | List Paging |
| `size` | `int` | No | List size. Max: 100 |

##### Response

``` http
    {
    "totalItems": 1,
    "payments": [
        {
            "id": 102,
            "amount": 3000.00,
            "paymentTypeId": 0,
            "date": "1970-01-01T00:02:03.456+00:00",
            "customerId": 1,
            "deletedTime": null
        }
    ],
    "totalPages": 1,
    "currentPage": 0
}
```

#### Get payment

```http
  GET /api/payment/${id}
```

| Parameter | Type     | Required     | Description                |
| :-------- | :------- | :------- | :------------------------- |
| `id` | `long` | Yes | Payment ID |


##### Response

``` http
    {
    "totalItems": 1,
    "payments": [
        {
            "id": 100,
            "amount": 3000.00,
            "paymentTypeId": 0,
            "date": "1970-01-01T00:02:03.456+00:00",
            "customerId": 1,
            "deletedTime": null
        }
    ],
    "totalPages": 1,
    "currentPage": 0
}
```
{
    "amount": 2050,
    "paymentTypeId": 1,
    "date": "2023-06-12T03:14:58.712+00:00",
    "customerId": 1
}


#### Create payment

```http
  POST /api/payment/${id}
```

| Parameter | Type     | Required     | Description                |
| :-------- | :------- | :------- | :------------------------- |
| `amount` | `long` | No | Payment ID |
| `customerId` | `long` | No | Payment ID |
| `paymentTypeId` | `long` | No | Payment ID |


##### Response

``` http
    {
    "id": 500,
    "amount": 1000,
    "paymentTypeId": 1,
    "date": "2023-06-13T08:35:18.787+00:00",
    "customerId": 1,
    "deletedTime": null
}
```

#### Update payment

```http
  PUT /api/payment/${id}
```

| Parameter | Type     | Required     | Description                |
| :-------- | :------- | :------- | :------------------------- |
| `amount` | `long` | No | Payment ID |
| `customerId` | `long` | No | Payment ID |
| `paymentTypeId` | `long` | No | Payment ID |


##### Response

``` http
    {
    "id": 500,
    "amount": 1000,
    "paymentTypeId": 1,
    "date": "2023-06-13T08:35:18.787+00:00",
    "customerId": 1,
    "deletedTime": null
}
```

#### Delete payment

The delete function uses soft-deletion method.

```http
  DEL /api/payment/${id}
```

| Parameter | Type     | Required     | Description                |
| :-------- | :------- | :------- | :------------------------- |
| `id` | `long` | Yes | Payment ID |


##### Response

``` http
    {
    "id": 500,
    "amount": 1000,
    "paymentTypeId": 1,
    "date": "2023-06-13T08:35:18.787+00:00",
    "customerId": 1,
    "deletedTime": "2023-06-13T08:39:45.849+00:00"
}
```


# Task #2: Filter and Pagination

Implementing filter and pagination purpose:
- User can retrieve specific filter that will reduce unnecessary data retrival.
- Pagination limits number of records per page, reducing the response size and improves performance especially for large data.

### API Endpoints

#### Get payments by filter

```http
  GET /api/payment/filter
```

| Parameter | Type     | Required     | Description                |
| :-------- | :------- | :------- | :------------------------- |
| `customerId` | `long` | Yes | Customer ID |
| `typeName` | `string` | Yes | Payment Type Name |
| `amount` | `long` | No | Payment Amount |
| `page` | `int` | No | List Paging |
| `size` | `int` | No | List Size. Max: 100 |


##### Response

``` http
    {
    "totalItems": 1,
    "payments": [
        {
            "id": 100,
            "amount": 3000.00,
            "paymentTypeId": 0,
            "date": "1970-01-01T00:02:03.456+00:00",
            "customerId": 1,
            "deletedTime": null
        }
    ],
    "totalPages": 1,
    "currentPage": 0
}
```

# Task #3: Handling Large Page Sizes
# Task #4: Frontend Search Component

<img src="https://i.imgur.com/fEQnDdt.jpg" alt="Screenshot" width="500" >

This repository provides a React.js app as its frontend with these features:
- **Real-time Results**. Search function provides real-time results as the user types.
- **Error Handling**. Show no records error when no results are found.
- **Mandatory filters**
    - _Customer ID_
    - _Payment Type Name_
- **Optional filter**
    - _Amount_

### Payment details

<img src="https://i.imgur.com/ZLGSG7Y.jpg" alt="Screenshot" width="500" >

### Payment not found

<img src="https://i.imgur.com/Uaept9V.jpg" alt="Screenshot" width="500" >

# Task #5: Addressing Slow Search Performance
