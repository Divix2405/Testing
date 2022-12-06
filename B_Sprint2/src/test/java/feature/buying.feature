Feature: Buying Shop items

Scenario: Reaching checkout page
Given User has opened url and signed in
And navigate to shop section
And clicked on wall stickers
And clicked on KIDS SHIPS SAILOR BIRDS WALL STICKERS & DECALS BY ASIAN PAINTS and type a correct pincode
When User clicks on buy now
Then User should see checkout page

Scenario: Add to cart verification
Given User has opened url and signed in
And navigate to shop section
And clicked on wall stickers
And clicked on KIDS SHIPS SAILOR BIRDS WALL STICKERS & DECALS BY ASIAN PAINTS and type a correct pincode
When User clicks on add to cart once
And open the bag icon in top right corner
Then user should see items in their cart

Scenario: User cannot buy without login
Given user has opened url 
And navigate to shop section
And clicked on wall stickers
And clicked on KIDS SHIPS SAILOR BIRDS WALL STICKERS & DECALS BY ASIAN PAINTS and type a correct pincode
When User clicks on buy now
Then User should see not see checkout page

Scenario: User can access bag without signing in 
Given user has opened url 
When User click on bag icon
Then Empty bag should open 


