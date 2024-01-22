# rewards-calculator-demo

Welcome to the Rewards Calculator Demo—a Spring Boot-based REST service designed for calculating and managing reward points. This service utilizes an in-memory H2 embedded database to efficiently store transaction-related data. Whether you want to retrieve rewards for the last three months or add a new transaction, this service has got you covered.


## API Details
### GET REWARDS
Retrieve the calculated rewards for a specific user in the last three months. Transactions for this period are fetched from the in-memory H2 database. The response provides detailed reward points for each month and the total reward points over the past three months.

Endpoint: GET localhost:8081/getReward/{userId}

### Note: The `monthCount` is configurable in the `application.yaml`. If a user wishes to retrieve data for the last 5 months, they need to configure `monthCount=5`. Subsequently, the response will be generated for the specified 5 months along with the `totalRewardPoints`.

Response
```json
{
  "reward": {
    "month-1-reward-point": 0,
    "month-2-reward-point": 118,
    "month-3-reward-point": 504,
    "totalRewardPoints": 622
  }
}
```

### CREATE TRANSACTION
Create a new transaction to the in-memory H2 database for a specific user. The request body should include two mandatory fields: transaction amount and date (in the valid format MM-dd-yyyy). The response is a simple string confirming the successful transaction creation.

Endpoint format: POST localhost:8081/transaction/{userId}

Request:

```json
{
  "amount" : 110,
  "date": "01-15-2024"
}
```

Response:
```json
{
  "message": "Transaction is created with amount : 110.0 for user : 3"
}
```

### OUTPUT
Pasted the Results from the postman in the output folder.

