
# Task #1
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
