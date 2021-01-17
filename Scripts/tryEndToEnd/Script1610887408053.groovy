import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper

response = WS.sendRequest(findTestObject('getToken'))

JsonSlurper slurper = new JsonSlurper()

Map parsedJson = slurper.parseText(response.getResponseText())

String access_token = parsedJson.access_token

GlobalVariable.access_token = ('Bearer ' + access_token)

response1 = WS.sendRequest(findTestObject('Employee/save_employee'))

WS.verifyElementPropertyValue(response1, 'success', 'Successfully Saved')

Map parsedJson1 = slurper.parseText(response1.getResponseText())

String id = parsedJson1.id

GlobalVariable.employee_id = id

response2 = WS.sendRequest(findTestObject('Employee/employee_detail'))

WS.verifyElementPropertyValue(response2, 'data.employeeId', GlobalVariable.employee_id)

response3 = WS.sendRequest(findTestObject('Employee/save_employee_dependents'))

WS.verifyElementPropertyValue(response3, 'success', 'Successfully Saved')

Map parsedJson2 = slurper.parseText(response3.getResponseText())

String number = parsedJson2.sequenceNumber

GlobalVariable.sequenceNumber = number

response4 = WS.sendRequest(findTestObject('Employee/update_employee_dependents'))

WS.verifyElementPropertyValue(response4, 'success', 'Successfully Updated')

response5 = WS.sendRequest(findTestObject('Employee/delete_employee_dependents'))

WS.verifyElementPropertyValue(response5, 'success', 'Successfully Deleted')

