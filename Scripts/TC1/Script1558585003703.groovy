import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.InputSource

// For explanation what "mixed content" is, see Michael Kay's book https://books.google.co.jp/books?id=Xw3_tzEJVEwC&pg=PA66&lpg=PA66&dq=xpath+mixed+content&source=bl&ots=05PtJQpt7K&sig=ACfU3U3DQm07UrEw7w5OLTnhB4ddS26iSg&hl=ja&sa=X&ved=2ahUKEwiuqqKA8LLiAhWEWrwKHb1aCpoQ6AEwA3oECAgQAQ#v=onepage&q=xpath%20mixed%20content&f=false
String tdWithMixedContent = '<td>Workflow <i class="icon-caret-right muted">::before</i> Record <i class="icon-caret-right muted">::before</i> New</td>'
println("tdWithMixedContent is '${tdWithMixedContent}'")

DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance()
DocumentBuilder db = dbf.newDocumentBuilder()
Document doc = db.parse(new InputSource(new StringReader(tdWithMixedContent)))
XPathFactory xpf = XPathFactory.newInstance();
XPath xpath = xpf.newXPath();

String expr

println("\n# XPath text() functions returns a NodeList when applied to an HTML Element with mixed content")
expr = "count(//td/text())"
println("'${expr}' returns " + (Number)xpath.evaluate(expr, doc, XPathConstants.NUMBER))

expr = "//td/text()[1]"
println("'${expr}' returns '" + (String)xpath.evaluate(expr, doc, XPathConstants.STRING) + "'")

expr = "//td/text()[2]"
println("'${expr}' returns '" + (String)xpath.evaluate(expr, doc, XPathConstants.STRING) + "'")

expr = "//td/text()[3]"
println("'${expr}' returns '" + (String)xpath.evaluate(expr, doc, XPathConstants.STRING) + "'")



println("\n# XPath contains(nodelist,pattern) function silently chooses only the 1st node of the nodelist given as 1st argument")
expr = '//td[contains(text(), "Workflow")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

expr = '//td[contains(text(), "Record")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN) + " !!!")

expr = '//td[contains(text(), "New")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN) + " !!!")



println("\n# you can choose a node out of the nodelist returned by text()")
expr = '//td[contains(text()[1], "Workflow")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

expr = '//td[contains(text()[2], "Record")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

expr = '//td[contains(text()[3], "New")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))



println("\n# normalize-space() function scans the descendent nodes of the target element recursively for text contents, concatinates them to a string, normalizes white space characters")
expr = "normalize-space(//td)"
println("'${expr}' returns \'" + (String)xpath.evaluate(expr, doc, XPathConstants.STRING) + "'")



println("\n# it is convenient to call contains() with normalize-space() as the 1st argument")
expr = '//td[contains(normalize-space(), "Workflow")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

expr = '//td[contains(normalize-space(), "Record")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

expr = '//td[contains(normalize-space(), "New")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

expr = '//td[contains(normalize-space(), "Workflow") and contains(normalize-space(), "Record") and contains(normalize-space(), "New")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

expr = '//td[contains(normalize-space(.), "Workflow")][contains(normalize-space(.), "Record")][contains(normalize-space(.), "New")]'
println("'${expr}' returns " + (Boolean)xpath.evaluate(expr, doc, XPathConstants.BOOLEAN))

println("")

