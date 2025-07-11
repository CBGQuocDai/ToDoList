link requirements: https://roadmap.sh/projects/todo-list-api
# Set up project 
- config project:
  - information account Mysql and redis
  - value of jwt secret
- run command: 
  ```
  mvn sringboost:run
  ```
# TODO LIST API DOCUMENT

Base URLs:

* <a href="http://localhost:8080">Generated server url: http://localhost:8080</a>

# Authentication

- use jwt
- library using : jjwt

# user-controller

<a id="opIdchangePassword"></a>

## PUT change password

PUT /user/changePassword

used to change password if it's necessary

> Body Parameters

```json
{
  "password": "123456789"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|[ChangePasswordRequest](#schemachangepasswordrequest)| no |none|

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success"
}
```

> 400 Response

```json
{
  "code": 1009,
  "message": "Password must have least 8 characters"
}
```

> 401 Response

```json
{
  "code": 1001,
  "message": "Unauthorized"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|change password success|[BaseResponse](#schemabaseresponse)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|change password failed|[ErrorResponse](#schemaerrorresponse)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|token is invalid or missing|[BaseResponse](#schemabaseresponse)|

# task-controller

<a id="opIdupdateTask"></a>

## PUT add task

PUT /todos/{id}

use to add task to list

> Body Parameters

```json
{
  "title": "Hehehhe",
  "description": "Buy milk, eggs, bread, and cheese"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int32)| yes |none|
|body|body|[TaskRequest](#schemataskrequest)| no |none|

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success",
  "data": {
    "id": 4,
    "title": "Buy tomato",
    "description": "Buy milk, eggs, bread, and cheese"
  }
}
```

> 400 Response

```json
{
  "code": 1007,
  "message": "missing value title"
}
```

> 401 Response

```json
{
  "code": 1001,
  "message": "Unauthorized"
}
```

> 404 Response

```json
{
  "code": 1003,
  "message": "Task not found "
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|create success|[BaseResponseTaskResponse](#schemabaseresponsetaskresponse)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|request miss field title|[ErrorResponse](#schemaerrorresponse)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|token is invalid or missing|[BaseResponse](#schemabaseresponse)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|not found task|[ErrorResponse](#schemaerrorresponse)|

<a id="opIddeleteTask"></a>

## DELETE delete task

DELETE /todos/{id}

use to delete task from list

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int32)| yes |none|

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success"
}
```

> 401 Response

```json
{
  "code": 1001,
  "message": "Unauthorized"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|logout|[BaseResponse](#schemabaseresponse)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|token is invalid or missing|[BaseResponse](#schemabaseresponse)|

<a id="opIdgetAllTasks"></a>

## GET get task list

GET /todos

get task list

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|page|query|integer(int32)| yes |none|
|limit|query|integer(int32)| yes |none|

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success",
  "data": [
    {
      "id": 1,
      "title": "Buy tomato",
      "description": "Buy milk, eggs, bread, and cheese"
    },
    {
      "id": 2,
      "title": "Buy tomato",
      "description": "Buy milk, eggs, bread, and cheese"
    },
    {
      "id": 3,
      "title": "Buy tomato",
      "description": "Buy milk, eggs, bread, and cheese"
    },
    {
      "id": 4,
      "title": "Buy tomato",
      "description": "Buy milk, eggs, bread, and cheese"
    },
    {
      "id": 5,
      "title": "",
      "description": "Buy milk, eggs, bread, and cheese"
    },
    {
      "id": 6,
      "title": "",
      "description": "Buy milk, eggs, bread, and cheese"
    }
  ],
  "page": 1,
  "limit": 10,
  "total": 6
}
```

> 401 Response

```json
{
  "code": 1001,
  "message": "Unauthorized"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|get task list success|[BaseResponseTaskListResponse](#schemabaseresponsetasklistresponse)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|token is invalid or missing|[BaseResponse](#schemabaseresponse)|

<a id="opIdcreateTask"></a>

## POST add task

POST /todos

use to add task to list

> Body Parameters

```json
{
  "title": "Hehehhe",
  "description": "Buy milk, eggs, bread, and cheese"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|[TaskRequest](#schemataskrequest)| no |none|

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success",
  "data": {
    "id": 4,
    "title": "Buy tomato",
    "description": "Buy milk, eggs, bread, and cheese"
  }
}
```

> 400 Response

```json
{
  "code": 1007,
  "message": "missing value title"
}
```

> 401 Response

```json
{
  "code": 1001,
  "message": "Unauthorized"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|create success|[BaseResponseTaskResponse](#schemabaseresponsetaskresponse)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|missing title|[ErrorResponse](#schemaerrorresponse)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|token is invalid or missing|[BaseResponse](#schemabaseresponse)|

# authenticate-controller

<a id="opIdregister"></a>

## POST Register user

POST /auth/register

Allow client create account

> Body Parameters

```json
{
  "email": "dai3gmail.com",
  "name": "tfasf",
  "password": "12345678"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|[RegisterRequest](#schemaregisterrequest)| no |none|

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYWk1QGdtYWlsLmNvbSIsImp0aSI6IjI2NmI4OTc4LTMzZTMtNGI4OS1hYjFiLWU3Nzc2MTAwMmY0NSIsImlhdCI6MTc1MjIzNjEyMywiZXhwIjoxNzUyMjM2MjIzfQ.buTXcYE0QqkTfZATvlBB03hUPvSX1u_IPUaBnpZbuxtPViTfY13BOZTefwFKbpHrWzFOIPFk25zgRMZ-IcEtXQ"
  }
}
```

> create fail

```json
{
  "code": 1005,
  "message": "Email already exists"
}
```

```json
{
  "code": 1009,
  "message": "Password must have least 8 characters"
}
```

```json
{
  "code": 1008,
  "message": "Email is invalid"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|create success|[BaseResponseTokenResponse](#schemabaseresponsetokenresponse)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|create fail|[ErrorResponse](#schemaerrorresponse)|

<a id="opIdlogout"></a>

## POST logout

POST /auth/logout

logout account

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success"
}
```

> 401 Response

```json
{
  "code": 1001,
  "message": "Unauthorized"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|logout|[BaseResponse](#schemabaseresponse)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|token is invalid or missing|[BaseResponse](#schemabaseresponse)|

<a id="opIdlogin"></a>

## POST user login

POST /auth/login

use to login

> Body Parameters

```json
{
  "email": "dai6@gmail.com",
  "password": "12345678"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|[LoginRequest](#schemaloginrequest)| no |none|

> Response Examples

> 200 Response

```json
{
  "code": 1000,
  "message": "success",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkYWk2QGdtYWlsLmNvbSIsImp0aSI6Ijc1YTZmNzcwLWNjMTctNGNkNi1hNzkwLWUyNmY1NjcxOWE5NSIsImlhdCI6MTc1MjI0NDk2OCwiZXhwIjoxNzUyMjQ1MDY4fQ.7cNy_QFr1EhTe4E683KxuWqu6X83EsVaIyMsVO3_xlRx5LSFVsWU1ZJbtwIh33w7MUrBvC234Iz80E8whw2dJQ"
  }
}
```

> 400 Response

```json
{
  "code": 1006,
  "message": "Email or password is incorrect"
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|login success|[BaseResponseTokenResponse](#schemabaseresponsetokenresponse)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|email or password is incorrect|[ErrorResponse](#schemaerrorresponse)|

# Data Schema

<h2 id="tocS_ChangePasswordRequest">ChangePasswordRequest</h2>

<a id="schemachangepasswordrequest"></a>
<a id="schema_ChangePasswordRequest"></a>
<a id="tocSchangepasswordrequest"></a>
<a id="tocschangepasswordrequest"></a>

```json
{
  "password": "stringst"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|password|string|true|none||none|

<h2 id="tocS_BaseResponse">BaseResponse</h2>

<a id="schemabaseresponse"></a>
<a id="schema_BaseResponse"></a>
<a id="tocSbaseresponse"></a>
<a id="tocsbaseresponse"></a>

```json
{
  "code": 0,
  "message": "string",
  "data": "string"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|code|integer(int32)|false|none||none|
|message|string|false|none||none|
|data|string|false|none||none|

<h2 id="tocS_ErrorResponse">ErrorResponse</h2>

<a id="schemaerrorresponse"></a>
<a id="schema_ErrorResponse"></a>
<a id="tocSerrorresponse"></a>
<a id="tocserrorresponse"></a>

```json
"string"

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|*anonymous*|string|false|none||none|

<h2 id="tocS_TaskRequest">TaskRequest</h2>

<a id="schemataskrequest"></a>
<a id="schema_TaskRequest"></a>
<a id="tocStaskrequest"></a>
<a id="tocstaskrequest"></a>

```json
{
  "title": "string",
  "description": "string"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|title|string|true|none||none|
|description|string|false|none||none|

<h2 id="tocS_BaseResponseTaskResponse">BaseResponseTaskResponse</h2>

<a id="schemabaseresponsetaskresponse"></a>
<a id="schema_BaseResponseTaskResponse"></a>
<a id="tocSbaseresponsetaskresponse"></a>
<a id="tocsbaseresponsetaskresponse"></a>

```json
{
  "code": 0,
  "message": "string",
  "data": {
    "id": 0,
    "title": "string",
    "description": "string"
  }
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|code|integer(int32)|false|none||none|
|message|string|false|none||none|
|data|[TaskResponse](#schemataskresponse)|false|none||none|

<h2 id="tocS_TaskResponse">TaskResponse</h2>

<a id="schemataskresponse"></a>
<a id="schema_TaskResponse"></a>
<a id="tocStaskresponse"></a>
<a id="tocstaskresponse"></a>

```json
{
  "id": 0,
  "title": "string",
  "description": "string"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|id|integer(int32)|false|none||none|
|title|string|false|none||none|
|description|string|false|none||none|

<h2 id="tocS_RegisterRequest">RegisterRequest</h2>

<a id="schemaregisterrequest"></a>
<a id="schema_RegisterRequest"></a>
<a id="tocSregisterrequest"></a>
<a id="tocsregisterrequest"></a>

```json
{
  "name": "string",
  "email": "string",
  "password": "stringst"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|name|string|true|none||none|
|email|string|false|none||none|
|password|string|false|none||none|

<h2 id="tocS_BaseResponseTokenResponse">BaseResponseTokenResponse</h2>

<a id="schemabaseresponsetokenresponse"></a>
<a id="schema_BaseResponseTokenResponse"></a>
<a id="tocSbaseresponsetokenresponse"></a>
<a id="tocsbaseresponsetokenresponse"></a>

```json
{
  "code": 0,
  "message": "string",
  "data": {
    "token": "string"
  }
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|code|integer(int32)|false|none||none|
|message|string|false|none||none|
|data|[TokenResponse](#schematokenresponse)|false|none||none|

<h2 id="tocS_TokenResponse">TokenResponse</h2>

<a id="schematokenresponse"></a>
<a id="schema_TokenResponse"></a>
<a id="tocStokenresponse"></a>
<a id="tocstokenresponse"></a>

```json
{
  "token": "string"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|token|string|false|none||none|

<h2 id="tocS_LoginRequest">LoginRequest</h2>

<a id="schemaloginrequest"></a>
<a id="schema_LoginRequest"></a>
<a id="tocSloginrequest"></a>
<a id="tocsloginrequest"></a>

```json
{
  "email": "string",
  "password": "string"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|email|string|false|none||none|
|password|string|true|none||none|

<h2 id="tocS_BaseResponseTaskListResponse">BaseResponseTaskListResponse</h2>

<a id="schemabaseresponsetasklistresponse"></a>
<a id="schema_BaseResponseTaskListResponse"></a>
<a id="tocSbaseresponsetasklistresponse"></a>
<a id="tocsbaseresponsetasklistresponse"></a>

```json
{
  "code": 0,
  "message": "string",
  "data": [
    {
      "id": 0,
      "title": "string",
      "description": "string"
    }
  ]
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|code|integer(int32)|false|none||none|
|message|string|false|none||none|
|data|[[TaskResponse](#schemataskresponse)]|false|none||none|

