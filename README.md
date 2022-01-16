# mail-worker

mail worker is just a very simple application who receiver messages with email contents from Azure Service Bus and send it with SendGrid

## Running in your local machine 

Clone the project, configure the environment variables and then in your term:

build the project
```bash
./gradlew build
````

and run
```bash
java -jar build/libs/mailworker-0.0.1-SNAPSHOT.jar
````

## Enviroment variables 

You need to set some env variables, you can use IntelliJ IDEA to set and run the app or just do in linux `export ENV="VALUE"` and in windows `set ENV="VALUE"`

need envs
```env

SENDGRID_API_KEY=
SERVER_PORT=
AZURE_CONNECTION_STRING=
MAIL_WORKER_QUEUE_NAME=

````

## Service Bus Message Body

To receive and process message, the body content of the message need to be 

```json
{
  "to": "johndoe@contoso.com"
  "from": "no-reply@contoso.com" //need to be allowed in sendgrid
  "subject": "This is an example email"
  "content": "Hi, John Doe! this can be a simple text or <p> some html mail </p>"
}
````

## Routes

This app just have `/health` route to kubernets or heroku see if app is running
