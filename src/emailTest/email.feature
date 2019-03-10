Story: 
As an Email service user,
I would like to be able to send an email with an image attached
so the receiver could receive the image

Normal Flow:
Sending email with an image attached to an address
     Given I am logged in on a new email page 
     When I add an email address under ¡°To¡±
     And I click ¡°attach¡± icon 
     And I select image in popup
     And I press ¡°Send¡±
     Then the email is sent

Alternate Flow:
Send email with an image attached to multiple email address
     Given I am logged in on a new email page 
     When I add an email address under ¡°To¡±
     And I add another email address under ¡°To¡±
     And I click ¡°attach¡± icon 
     And I select image in popup
     And I press ¡°Send¡±
     Then the email is sent

Alternate Flow:
Send email with multiple images attached to single email address
     Given I am logged in on a new email page 
     When I add an email address under ¡°To¡±
     And I click ¡°attach¡± icon 
     And I select image in popup
     And I click ¡°attach¡± icon 
     And I select another image in popup
     And I press ¡°Send¡±
     Then the email is sent

Alternate Flow:
Send email with multiple images attached to multiple email address
     Given I am logged in on a new email page 
     When I add an email address under ¡°To¡±
     And I add another email address under ¡°To¡±
     And I click ¡°attach¡± icon 
     And I select image in popup
     And I click ¡°attach¡± icon 
     And I select another image in popup
     And I press ¡°Send¡±
     Then the email is sent

Error Flow:
	Send email with an image attached to no address
     Given I am logged in on a new email page 
     And I click ¡°attach¡± icon 
     And I select image in popup
     And I click ¡°attach¡± icon 
     And I select another image in popup
     And I press ¡°Send¡±
     Then the an error message pops up
