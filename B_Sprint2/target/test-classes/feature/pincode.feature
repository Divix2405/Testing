Feature: Pincode functionality in different sections

Scenario: Giving invalid six digit pincode
Given User has opened the site
And navigated to shop section
And clicked on wall stickers option
And clicked on kids ships sailor birds- wall sticker and user enters a invalid six digit integer pincode
When Clicks on check
Then Service message is displayed the area is not serivceable

Scenario: Seeing the pincode enter form
Given User has opened the site
And navigated to shop section
When Clicked on wall stickers option
And User clicks add to cart on the first item
Then User should see enter pincode form

Scenario: Check if pincode is being verified or not by giving nothing in pincode
Given User has opened the site 
And navigated to shop section
And clicked on wall stickers option
When clicked on kids ships sailor birds- wall sticker and user enters nothing in pincode
And clicks on check
Then Service message is displayed

Scenario: Check if pincode is being verified or not by giving correct pincode
Given User has opened the site 
And navigated to shop section
And clicked on wall stickers option
When clicked on kids ships sailor birds- wall sticker and user enters a valid six digit integer pincode
And clicks on check
Then Service message is displayed the area is serivceable

Scenario: Item cannot be added to cart without pincode
Given User has opened the site
And navigated to shop section
And clicked on wall stickers option
When clicked on kids ships sailor birds- wall sticker and User clicks on Add to cart
Then Appropriate error message should be shown under pincode bar.


