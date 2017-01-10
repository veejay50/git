Feature: Contact Us Page	
		 As an end user
		 I want a contact us page
		 So that i can find out more about QAWorks exciting services!!
		  
 Scenario: Valid Submission  
         Given I am on the QAWorks Site
         Then I should be able to contact QAWorks with the following information
         | name    | j.Bloggs                                  |
         | email   | j.Bloggs@qaworks.com                      | 
         | message | please contact me I want to find out more |    
        And I wait for 3 sec
        Then I close browser 