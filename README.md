# Game

## Introduction

This is Ineor's task for new recruits. We are expecting you take some time and implement following services so they work
as explained in following paragraphs. Craft code you would be proud of and you would leave your coworker to maintain.
You are required to use Spring boot framework and Java 11. We ask you that you code with help of streams and other
features found in versions after Java 8. You can use any database you would like, we are mostly using PostgreSQL thats
why this project is preconfigured for it.

For any needed information or help on this task you can contact domen.petric@ineor.si and mitja.gomboc@ineor.si. After
we get returned finished task you will get additional smaller task that you will perform at our office in time of
interview. So please bring your laptop with your preferred IDE.

Note that task is imaginary and is here to test your programming principles not to challenge real world logic.

## What we are building here

We are building here a betting and payout service for multiple different lottery games with high input traffic. Each
game has its own specifics, but it has similar inputs. Way of handling/sending data from betting service to payout
service is up to you, but you keep in mind that you have high load application occasionally running on multiple
instances for each service. You must ensure that some data is not processed multiple times and on the other hand if
service crashes work will be continued from where it ended.

1) You need to build **betting service** which exposes following REST APIs:
    1) Insert new betting ticket
    2) Edit existing betting ticket that was not yet evaluated
    3) Delete existing betting ticket that was not yet evaluated
    4) List betting tickets with pagination (Optional bonus filtering and sorting )
    5) Evaluation cronjob that runs every 5 minutes and checks all tickets with match time before current time and:
        1) even ID as winning ticket,
        2) odd ID as losing ticket.
    6) Incoming ticket has following data:
        1) Source country one of(AT, BE, BG, SI, SK, SE, UK)
        2) Quota
        3) Bet amount
        4) Game DateTime
        5) GameId

2) You are also building **payout service** which receives only winning tickets from betting service and does following:
    1) Receives taxation data from url GET https://euvatrates.com/rates.json <- lets say data can change only every 5
       minutes therefore you are not checking URL for every bet
    2) Calculate taxation for each ticket and write following calculated ticket data to database:
        1) ticketID
        2) Source country
        3) Bruto amount (Quota*Bet amount)
        4) Tax Amount (defined in chapter iii)
        5) Payout Amount (Bruto amount - tax amount)
    3) Tax amount is calculated different based on first letter of a country:
        1) Countries starting with A -> taxation = standard_rate*bruto_amount
        2) Countries starting with B -> taxation = reduced_rate_alt*bruto_amount
        3) Countries starting with S -> taxation = (standard_rate+reduced_rate_alt)/2*bruto_amount
        4) Countries starting with U -> taxation = standard_rate*(reduced_rate_alt*bruto_amount)
    4) All monetary values must be calculated and rounded to 2 decimal values (HALF_UP)
    5) Optionally add unit tests for taxation calculations implemented in 3)
3) Build containers with all applications
4) Write docker-compose script for running whole application
5) Document how to use your provided applications.

## Documentation
