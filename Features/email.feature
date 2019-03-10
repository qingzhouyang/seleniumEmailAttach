Feature: send email with image attached

As an Email service user,
I would like to be able to send an email with an image attached. 
so the receiver could receive the image


Scenario: Sending email with an image attached to an address
     Given I am logged in on a new email page 
     When I add an email address under "To"
     And I click "attach" icon 
     And I select image in popup
     And I press "Send"
     Then the email is sent
     
Scenario: Send email with multiple images attached to single email address
     Given I am logged in on a new email page 
     When I add an email address under "To"
     And I click "attach" icon 
     And I select images in popup
     And I press "Send"
     Then the email is sent

Scenario: Send email with one image attached to two email addresses
     Given I am logged in on a new email page 
     When I add two email addresses under "To"
     And I click "attach" icon 
     And I select image in popup
     And I press "Send"
     Then the email is sent


Scenario: Send email with two images attached to two email addresses
     Given I am logged in on a new email page 
     When I add two email addresses under "To"
     And I click "attach" icon 
     And I select images in popup
     And I press "Send"
     Then the email is sent


