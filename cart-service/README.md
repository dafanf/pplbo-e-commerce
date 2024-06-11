# Doc

## Prerequisite

- database mysql with name **cart_service**
- add mysql root's password in application.properties

## Swagger

swagger-ui at `http://localhost:8084/swagger-ui/index.html#/`

### Sample order json to **api/orders** method POST

```json
{
  "orderDate": "2023-05-29T15:30:00Z",
  "orderStatus": "Processing",
  "totalPrice": 5.75,
  "orderLineItems": [
    {
      "quantity": 2,
      "productId": 1
    }
  ],
  "shipping": {
    "shippingName": "JNE",
    "shippingPrice": 10.0,
    "shippingStatus": "Pending",
    "shippingAddress": "12 Main Street, Anytown, AT 12345"
  },
  "customer": {
    "firstName": "fardan",
    "lastName": "aljihad"
  },
  "paymentId": "3"
}
```
