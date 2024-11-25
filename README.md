1. `GET /companies`
    - Status: 200
2. `GET /companies/{id}`
    - Status: 200
3. `GET /companies/{id}/employees`
    - Status: 200
4. `GET /companies?page={page}&size={size}`
    - Status: 200
5. `PUT /companies/{id}/employees/{id}`
    - Status: 200
    - Body:
        ```json
        {
          "name": "name",
          "age": 23,
          "gender": "FEMALE",
          "salary": 1230.0
        }
        ```
6. `POST /companies`
    - Status: 201
        ```json
        {
          "name": "name"
        }
        ```
7. `DELETE /companies/{id}`
    - Status: 204