#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Mar 25 20:47:15 2020

@author: abhijithneilabraham
"""


# A very simple Flask Hello World app for you to get started with...

from flask import Flask
from dbconnect import dbupdate

app = Flask(__name__)

@app.route('/')
def hello_world():
    rem,flag=dbupdate()
    return "remaining time"+str(rem)+"flag"+str(flag)

