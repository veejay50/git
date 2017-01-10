Feature: QAWorks contact
        As a QAWorks visitor I want to send information using contact us page
 
 Scenario: Send information using contact us page on QaWorks site
        Given I navigate to "http://www.qaworks.com"
        And I click on element having css "#menu > li:nth-child(1) > a"         
        And I enter "j.Bloggs" into input field having id "ctl00_MainContent_NameBox"
        And I enter "j.Bloggs@qaworks.com" into input field having id "ctl00_MainContent_EmailBox"
        And I enter "please contact me I want to find our more" into input field having id "ctl00_MainContent_MessageBox"
        And I click on element having id "ctl00_MainContent_SendButton"  
	    And I wait for 3 sec
        Then I close browser   
        