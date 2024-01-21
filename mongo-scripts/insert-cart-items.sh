#!/bin/bash

# Your logic to insert cart items
mongo mongodb://localhost:27017/idealo --eval 'db.items.insertMany([
                                                   {
                                                       name: "A",
                                                       quantity: 1,
                                                       price: 40.0
                                                   },
                                                   {
                                                       name: "B",
                                                       quantity: 1,
                                                       price: 50.0
                                                   },
                                                   {
                                                       name: "C",
                                                       quantity: 1,
                                                       price: 25.0
                                                   },
                                                   {
                                                       name: "D",
                                                       quantity: 1,
                                                       price: 20.0
                                                   },
                                                   {
                                                       name: "E",
                                                       quantity: 1,
                                                       price: 100.0
                                                   },
                                               ]);
'
