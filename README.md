# Brief info
This project is an application that aims to view plane tickets, apartures, and a service for booking ticket.

# communication pipeline
## `Database.java`
---
`AddFlight`  
**Payload**
```json
{
"destination": "DESTINATION",
"date": "FLIGHT_DATE",
"freeseats": 0
}
```

| Field         | Type     | Description                            |
| ------------- | -------- | -------------------------------------- |
| *destination* | `String` | Destination where the flight will go   |
| *date*        | `String` | Date of departure (format: YYYY-MM-DD) |
| *freeseats*   | `int`    | Number of available seats              |

**Return**
```json
{
"status": 1,
"flightid": "3e89a7ec"
}
```

| Field      | Type     | Description      |
| ---------- | -------- | ---------------- |
| *status*   | `int`    | 1 - ok 0 - error |
| *flightid* | `String` | ID of flight     |

---
`RemoveFlight`  
**Payload**
```json
{
"flightid": "3e89a7ec"
}
```

| Field       | Type     | Description                            |
| ----------- | -------- | -------------------------------------- |
| *flightid*  | `String` | ID of flight                           |

**Return**
```json
{
"status": 1
}
```

| Field      | Type     | Description      |
| ---------- | -------- | ---------------- |
| *status*   | `int`    | 1 - ok 0 - error |

---
`AddBooking`  
**Payload**
```json
{
  "flightid": "3e89a7ec",
  "name": "John Doe",
  "numberoftickets": 1
}
```

| Field             | Type     | Description                |
| ----------------- | -------- | -------------------------- |
| *flightid*        | `String` | Flight ID to book          |
| *name*            | `String` | Full name of the passenger |
| *numberoftickets* | `int`    | Number of tickets          |

**Return**
```json
{
"status": 1,
"bookingid": "a2c1b289"
}
```

| Field             | Type     | Description       |
| ----------------- | -------- | ----------------- |
| *status*          | `int`    | 1 - ok 0 - error  |
| *bookingid*       | `String` | ID of booking     |

---
`CancelBooking`  
**Payload**
```json
{
  "bookingid": "a2c1b289"
}
```

| Field             | Type     | Description                |
| ----------------- | -------- | -------------------------- |
| *bookingid*       | `String` | ID of booking              |

**Return**
```json
{
"status": 1
}
```

| Field       | Type     | Description      |
| ----------- | -------- | ---------------- |
| *status*    | `int`    | 1 - ok 0 - error |


# author(s)
- aklerza - project management
- arif hajiyev - database implement
- nazrin muradli - logic implement
- tunjay - user interface implement