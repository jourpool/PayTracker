
# PayTacker

Project Assignment for EP R&D




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
