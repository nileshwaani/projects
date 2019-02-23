# Dynamic Restful Service Builder

**RESTful Prototype** is a dynamic RESTful service builder. It is RESTful web service that you can use as a tool to create/build new RESTful web services on the fly.

It neither generates any source code, nor stores data in persistent storage. It keeps everything, including the endpoints information, in memory.

As an example, let's say that you want to build an employee management system. The application will have features to create new employees, show the list of employees, show employee details, modify employee, delete employee. Designing REST endpoints will be one of the steps in the inital phase of development. If there is separate team for front end development, the implementation of these REST APIs should be ready to start front end development.

RestPrototype helps you to create prototypes of such RESTful web APIs. Using this, you can quickly create endpoints like:

Request|Comment
---|---
GET /employees                 | Get all employees.
GET /employees/{employeeId}    | Get employee for given employeeId.
POST /employees                | Create new employee.
PUT /employees/{employeeId}    | Update an existing employee.
DELETE /employees/{employeeId} | Delete employee for given employeeId.

<br/>
Feel free to extend the RESTful Prototype adding the features as per your requirements.

## Running the service

Follow the below instructions to set up and start the RestPrototype service on your local machine:

1. Clone the repository on your machine.

2. By default, the service uses the port 8080. To change the port, add below property in **application.properties**.

3. Open the command prompt and change to the directory RestPrototype.

4. Start the service using `gradle bootRun`

## How it works?

**RestPrototype** exposes the endpoint */endpoints* through which you can create and manage new endpoints.

For example, to create a new endpoint */employees*, send HTTP PUT request to */endpoints/employees* with no request body.

The convention to use the newly created endpoint */employees* is explained below:

1. You can then invoke basic CRUD operations on */employees* using respective HTTP verbs.

2. To create a new employee with ID = 10, send HTTP PUT request to */employees/10* passing JSON representation of employee data like:      

```
  {
    "employeeId":1
    "firstName":"John",
    "lastName":"Williams",
    "emailId":"j.williams@test.com",
    "birthDate":"1980-01-01"
  }
```

3. To get employee with ID = 10, send HTTP GET request to */employees/10*. This will return JSON representation of employee.

More explanation about endpoints will follow in **Dynamic Endpoint Documentation** section.

## RestPrototype Documentation
### PUT /endpoints/{endpointURL}

Creates a new endpoint with URL */endpointURL*.

**Response**

Endpoint data containing URL with empty objects list.

**Example**

Sending PUT request to */endpoints/employees* will create a new endpoint */employees*. The response will be:

```
{
  "url": "employees",
  "objects": []
}
```
---
### GET /endpoints/{endpointURL}

Gets the details about specific endpoint.

**Request**

_Parameter_

endpointURL - The endpoint URL.

**Response**

Details about the requested endpoint with list of objects.

**Example**

Sending GET request to */endpoints/employees* will give details about */employees* endpoint. This also contains the list of employees objects with their details:
```
{
  "url": "employees",
  "objects": [
    {
        "key": 1,
        "object": {
            "employeeId": 1,
            "firstName": "James",
            "lastName": "Butt",
            "dateOfBirth": "01-JAN-1981"
        }
    },
    {
        "key": 2,
        "object": {
            "employeeId": 2,
            "firstName": "Kris",
            "lastName": "Marrier",
            "dateOfBirth": "02-JAN-1981"
        }
    }
  ]
}
```

The `objects` is the list of employees objects.
The `key` is the identifier like employee ID.
The `object` is the JSON representation of the employee object.

---
### GET /endpoints

Gets the details of all the endpoints.

**Response**

Details about all the endpoints. This includes objects for all the endpoints.

```
[
  {
      "url": "departments",
      "objects": [
          {
              "key": 1,
              "object": {
                  "departmentId": 1,
                  "code": "DEV",
                  "location": "London"
              }
          }
      ]
  },
  {
    "url": "employees",
    "objects": [
      {
          "key": 1,
          "object": {
              "employeeId": 1,
              "firstName": "James",
              "lastName": "Butt",
              "dateOfBirth": "01-JAN-1981"
          }
      },
      {
          "key": 2,
          "object": {
              "employeeId": 2,
              "firstName": "Kris",
              "lastName": "Marrier",
              "dateOfBirth": "02-JAN-1981"
          }
      }
    ]
  }
]
```
---
### POST /endpoints

Creates multiple endpoints along with its objects.

**Request**

_Body_

Details of the endpoints to be created along with its objects.
```
[
  {
      "url": "departments",
      "objects": [
          {
              "key": 1,
              "object": {
                  "departmentId": 1,
                  "code": "DEV",
                  "location": "London"
              }
          }
      ]
  },
  {
    "url": "employees",
    "objects": [
      {
          "key": 1,
          "object": {
              "employeeId": 1,
              "firstName": "James",
              "lastName": "Butt",
              "dateOfBirth": "01-JAN-1981"
          }
      },
      {
          "key": 2,
          "object": {
              "employeeId": 2,
              "firstName": "Kris",
              "lastName": "Marrier",
              "dateOfBirth": "02-JAN-1981"
          }
      }
    ]
  }
]
```
---
### DELETE /endpoints/{endpointURL}

Deletes the given endpoint.

**Request**

_Parameters_

endpointURL - The endpoint to be deleted.

**Example**

Sending DELETE to */endpoints/employees* will delete the endpoint */employees*.

---
### DELETE /endpoints

Deletes all the endpoints.

## Dynamic Endpoints Documentation
### PUT /{endpointURL}/{identifier}

Creates a new object with the given identifier for the given endpoint.

**Request**

_Parameter_

endpointURL - The name of the dynamic service. For example `employees`
identifier - The ID of the newly created object. For example, ID of the employee.

_Body_

JSON representation of the object to be created.

**Response**

JSON representation of the object created.

**Example**

To create new employee with ID = 10, send PUT request to */employees/10* and pass below JSON in the request body.

```
{
  "employeeId": 10,
  "firstName": "James",
  "lastName": "Butt",
  "dateOfBirth": "01-JAN-1981"
}
```
---
### GET /{endpointURL}

Gets all the objects for given endpoint.

**Request**

_Parameter_

endpointURL - The name of the dynamic service. For example `employees`

**Response**

All the objects for the given service.

```
[
  {
    "employeeId": 1,
    "firstName": "James",
    "lastName": "Butt",
    "dateOfBirth": "01-JAN-1981"
  },
  {
    "employeeId": 2,
    "firstName": "Kris",
    "lastName": "Marrier",
    "dateOfBirth": "02-JAN-1981"
  },
  {
    "employeeId": 3,
    "firstName": "Alisha",
    "lastName": "Rim",
    "dateOfBirth": "03-JAN-1981"
  }
]
```
---
### GET /{endpointURL}/{identifier}

Gets the object for given service and for given identifier.

**Request**

_Parameter_

endpointURL - The name of the dynamic service. For example `employees`
identifier - The ID of the requested object. For example, ID of the employee.

**Response**

The object for the given service and given identifier.

**Example**

To get the details about employee with ID = 10, send GET request to */employees/10*. This will return below response JSON:

```
{
  "employeeId": 1,
  "firstName": "James",
  "lastName": "Butt",
  "dateOfBirth": "01-JAN-1981"
}
```
---
### DELETE /{endpointURL}/{identifier}

Delets the object for given service and for given identifier.

**Request**

_Parameter_

endpointURL - The name of the dynamic service. For example `employees`
identifier - The ID of the object to be deleted. For example, ID of the employee.

**Example**

To delete employee with ID = 10, send DELETE request to */employees/10*.

---
### DELETE /{endpointURL}

Deletes all the objects for given service.

**Request**

_Parameter_

endpointURL - The name of the dynamic service. For example `employees`

**Example**

To delete all employees, send DELETE request to */employees*.
