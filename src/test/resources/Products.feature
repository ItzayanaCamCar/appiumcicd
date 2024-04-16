Feature: Product scenarios



  Scenario Outline: Validate product info on Products page
    Given I am logged in
    Then  The product is listed with title "<title>" and price "<price>"
    Examples:
      | title                   | price  |
      | Sauce Labs Bolt T-Shirt | $15.99 |


  Scenario Outline: Validate product info on Product Details page
    Given I am logged in
    When I click product title "<title>"
    Then  I should be on product details page with title "<title>", price "<price>" and description "<description>"
    Examples:
      | title               | price  | description                                                                                                                            |
      | Sauce Labs Backpack | $29.99 | carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection. |
