package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();

	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {

		if (this.driver != null) {
			driver.quit();
		}



	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}
	@Test
	public void getHomePage() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	private void doLogout(){

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/home");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutsubmit")));
		WebElement buttonSignUp = driver.findElement(By.id("logoutsubmit"));
		buttonSignUp.click();

	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depening on the rest of your code.
		*/
		//redirection to login page after seccsusful sign up
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {

		//if the person is not signed in it will get redirected to the login page, if he is signed in it will be redirected to the home page

		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

		driver.get("http://localhost:" + this.port + "/udacityORbasicallyAnyRandomURL");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

		//check if the home page can be acceced by unlogged in user
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

		//redirection after logging in
		doLogIn("RT","123");
		driver.get("http://localhost:" + this.port + "/udacityORbasicallyAnyRandomURL");
		Assertions.assertEquals("http://localhost:" + this.port + "/home", driver.getCurrentUrl());


		//test that the home page is not accecable after logging out
		doLogout();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());


	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));


	doLogout();
	}

	@Test
	public void testNotesFunctions(){

		doLogIn("LFT", "123");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		WebElement notesNavButton = driver.findElement(By.id("nav-notes-tab"));
		notesNavButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("showNoteModalButton")));
		WebElement showNoteModalButton = driver.findElement(By.id("showNoteModalButton"));
		showNoteModalButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.click();
		noteTitle.sendKeys("udacity cource");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-description")));
		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.click();
		noteDescription.sendKeys("this is done by unit testing");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notesSubmit")));
		WebElement notesButton = driver.findElement(By.id("notesSubmit"));
		notesButton.click();
		//making sure the user logged out
		doLogout();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());

		checkNotes("udacity cource","this is done by unit testing");

		System.out.println("the note have been added");

		doLogIn("LFT", "123");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		notesNavButton = driver.findElement(By.id("nav-notes-tab"));
		notesNavButton.click();


		WebElement editButton = driver.findElement(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/button"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/button")));

		//Edit note
		editButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.click();
		noteTitle.clear();
		noteTitle.sendKeys("update note");

		noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.click();
		noteDescription.clear();
		noteDescription.sendKeys("this note has been updated");

		notesButton = driver.findElement(By.id("notesSubmit"));
		notesButton.click();

		// check if the note have been updated
		checkNotes("update note","this note has been updated");
		System.out.println("the note have been updated");

		doLogIn("LFT", "123");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		notesNavButton = driver.findElement(By.id("nav-notes-tab"));
		notesNavButton.click();

		WebElement deleteButton = driver.findElement(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/a"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/a")));

		deleteButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		notesNavButton = driver.findElement(By.id("nav-notes-tab"));
		notesNavButton.click();
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("showNoteModalButton")));

		//check if the button is deleted
		try {
			deleteButton =	driver.findElement(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/a"));
		}
		catch(Exception e) {
			Assertions.assertEquals(1,1);
			System.out.println("the row is deleted");
		}



	}

	public void checkNotes(String title,String desc){

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		doLogIn("LFT", "123");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		WebElement notesNavButton1 = driver.findElement(By.id("nav-notes-tab"));
		notesNavButton1.click();


		WebElement editButton = driver.findElement(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/button"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/button")));

		WebElement deleteButton = driver.findElement(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/a"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[1]/a")));

		WebElement showTitleInTable = driver.findElement(By.xpath("//*[@id='userTable']/tbody/tr[1]/th"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userTable']/tbody/tr[1]/th")));
		Assertions.assertEquals(title,showTitleInTable.getText());

		WebElement showDescInTable = driver.findElement(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[2]"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userTable']/tbody/tr[1]/td[2]")));
		Assertions.assertEquals(desc,showDescInTable.getText());

		doLogout();
	}
	@Test
	public void testCredFunctions(){

		doLogIn("LFT", "123");

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		WebElement credentialsNavButton = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("showCredentialModalButton")));
		WebElement showCredentialModal = driver.findElement(By.id("showCredentialModalButton"));
		showCredentialModal.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement credentialURL = driver.findElement(By.id("credential-url"));
		credentialURL.click();
		credentialURL.sendKeys("google.com");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		WebElement credentialusername = driver.findElement(By.id("credential-username"));
		credentialusername.click();
		credentialusername.sendKeys("Faisl");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		WebElement credPassword = driver.findElement(By.id("credential-password"));
		credPassword.click();
		credPassword.sendKeys("1234");


		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialSubmitButton")));
		WebElement credentialSubmitButton = driver.findElement(By.id("credentialSubmitButton"));
		credentialSubmitButton.click();


		doLogout();


		//check if the credintals have been added
		checkCredintals("google.com","Faisl","1234");
		System.out.println("the Credintials have been added");

		//editing the Credintals
		doLogIn("LFT", "123");




		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		credentialsNavButton = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavButton.click();

		WebElement editButton = driver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/button"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/button")));
		editButton.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		credentialURL = driver.findElement(By.id("credential-url"));
		credentialURL.click();
		credentialURL.clear();
		credentialURL.sendKeys("google.com");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		credentialusername = driver.findElement(By.id("credential-username"));
		credentialusername.click();
		credentialusername.clear();
		credentialusername.sendKeys("Faissl");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-username")));
		credPassword = driver.findElement(By.id("credential-password"));
		credPassword.click();
		credPassword.clear();
		credPassword.sendKeys("12345678");


		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credentialSubmitButton")));
		credentialSubmitButton = driver.findElement(By.id("credentialSubmitButton"));
		credentialSubmitButton.click();

		doLogout();
		checkCredintals("google.com","Faissl","12345678");

		System.out.println("the Credintials have been edited");
		//delete credintial
		doLogIn("LFT", "123");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		credentialsNavButton = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavButton.click();

		WebElement deleteButton = driver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/a"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/a")));
		deleteButton.click();

		credentialsNavButton = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavButton.click();

		try {
			deleteButton =	driver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/a"));
			Assertions.fail();
		}
		catch(Exception e) {
			Assertions.assertTrue(true);
			System.out.println("the row is deleted");
		}

	}

	public void checkCredintals(String url,String username,String password){

		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		doLogIn("LFT", "123");

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		WebElement credentialsNavButton = driver.findElement(By.id("nav-credentials-tab"));
		credentialsNavButton.click();


		WebElement showURLInTable = driver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/th"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='credentialTable']/tbody/tr/th")));
		Assertions.assertEquals(url,showURLInTable.getText());

		WebElement showUsernameInTable = driver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[2]"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='credentialTable']/tbody/tr/td[2]")));
		Assertions.assertEquals(username,showUsernameInTable.getText());


		WebElement showPasswordInTable = driver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[3]"));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='credentialTable']/tbody/tr/td[3]")));
		//check that the password is encrypted
		Assertions.assertNotEquals(password,showPasswordInTable.getText());

		doLogout();






}}
