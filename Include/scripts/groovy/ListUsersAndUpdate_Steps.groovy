import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import internal.GlobalVariable

public class ListUsersAndUpdate_Steps {

	@Given("I get the user information")
	def getTheUserInfo() {

		GlobalVariable.latest_response = WS.sendRequestAndVerify(findTestObject('RESTWebServices/ListUsers'))
	}

	@Then("The response code is (.*)")
	def verifyResponseCode(String code) {

		WS.verifyResponseStatusCode(GlobalVariable.latest_response, code as Integer)
	}

	@Then("The email of one of the users is (.*)")
	def verifyEmail(String email) {

		WS.verifyElementPropertyValue(GlobalVariable.latest_response, 'data[4].email', email)
	}
	
	@Given("I have the user information fetched from ListUsersAPI")
	def fetchUserInformation() {
		
		GlobalVariable.latest_response = WS.sendRequest(findTestObject('RESTWebServices/ListUsers'))
	}
	
	@Given("I update the user information through UpdateUserAPI")
	def updateUserInformation() {
		
		def slurper = new groovy.json.JsonSlurper()
		def result = slurper.parseText(GlobalVariable.latest_response.getResponseBodyContent()) 
		def value = result.data[4].first_name //Fetch the value of the first name from ListUsersAPI 
		println('Value is: ' + value)
		
		WS.sendRequestAndVerify(findTestObject('RESTWebServices/UpdateUser', [('username') : value]))
		
		/* This is another way of assigning the value to global variable and use it for update
		 
		 GlobalVariable.username = value
		 WS.sendRequestAndVerify(findTestObject('RESTWebServices/UpdateUser'))
		  
	 */
	}
	
	
}
