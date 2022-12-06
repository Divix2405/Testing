Feature: Accessing shop content
I want to be able to access,navigate and use shop section of website

Scenario: Clicking and redirection from first sliding image
Given User has opened the site in browser
When navigated to Shop section
Then User clicks on first sliding image and should be redirected to respective site

Scenario: Check if color selection tool is displayed
Given User has opened the site in browser
And Navigated to shop section
Then User should see color selection tool category

Scenario: Check if color selection tool has content
Given User has opened the site in browser
When navigated to Shop section
Then User clicks on color selection tool and User should see content inside color selection tool

Scenario: Observing empty cart
Given User has opened the site in browser
When User clicks on bag icon in the header in right corner
Then User should see the message that cart is empty
