A Study on XPath function text(), contains() and normalize-space()
===============

## What is this project for


A post in the [Katalon Forum](https://forum.katalon.com/) ["XPath writing example"](https://forum.katalon.com/t/xpath-writing-example/25932/6) raised an interesting question. This post motivated me to study XPath functions `text()`, `contains()` and `normalize-space()` again.

## How to see the demo

1. download this project, unzip it
2. open the project with Katalon Stduio
3. open `Test Cases/TC1`, and runit
4. check the console output

## Research result

The message from the TC1 shows all I got.

```
tdWithMixedContent is '<td>Workflow <i class="icon-caret-right muted">::before</i> Record <i class="icon-caret-right muted">::before</i> New</td>'

# XPath text() functions returns a NodeList when applied to an HTML Element with mixed content
'count(//td/text())' returns 3.0
'//td/text()[1]' returns 'Workflow '
'//td/text()[2]' returns ' Record '
'//td/text()[3]' returns ' New'

# XPath contains(nodelist,pattern) function silently chooses only the 1st node of the nodelist given as 1st argument
'//td[contains(text(), "Workflow")]' returns true
'//td[contains(text(), "Record")]' returns false !!!
'//td[contains(text(), "New")]' returns false !!!

# you can choose a node out of the nodelist returned by text()
'//td[contains(text()[1], "Workflow")]' returns true
'//td[contains(text()[2], "Record")]' returns true
'//td[contains(text()[3], "New")]' returns true

# normalize-space() function scans the descendent nodes of the target element recursively for text contents, concatinates them to a string, normalizes white space characters
'normalize-space(//td)' returns 'Workflow ::before Record ::before New'

# it is convenient to call contains() with normalize-space() as the 1st argument
'//td[contains(normalize-space(), "Workflow")]' returns true
'//td[contains(normalize-space(), "Record")]' returns true
'//td[contains(normalize-space(), "New")]' returns true
'//td[contains(normalize-space(), "Workflow") and contains(normalize-space(), "Record") and contains(normalize-space(), "New")]' returns true
'//td[contains(normalize-space(.), "Workflow")][contains(normalize-space(.), "Record")][contains(normalize-space(.), "New")]' returns true


```
