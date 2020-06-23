package com.qa.exploreSelenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;

import com.thoughtworks.selenium.webdriven.commands.Click;

public class GitHubLauncher {
	WebDriver driver;
	private final String userName = "abhinavqa";
	private final String password = "Abhinav@0911";
	String URL = "https://github.com/";
	String repoName = "Repo2";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GitHubLauncher obj = new GitHubLauncher();
		obj.setDriver();
		obj.loadGitHub();
		obj.signIn();
		obj.createRepo();
		obj.browseIssueUrl();
		obj.createIssue();
		obj.createAnohterIssue();
		obj.addingComment();
		obj.deleteRepo();
	}

	private WebDriver setDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\setups\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

	private void loadGitHub() {
		driver.get(URL);
		String title = driver.getTitle();
		System.out.println("Current Title is " + title);

	}

	private void signIn() {
		WebElement loginButton = driver.findElement(By.xpath("//a[@class='HeaderMenu-link no-underline mr-3']")); // locating
																													// sign
																													// in
																													// button
		loginButton.click();
		WebElement userNameEl = driver.findElement(By.id("login_field"));
		userNameEl.sendKeys(userName); //
		WebElement passwordEl = driver.findElement(By.id("password"));
		passwordEl.sendKeys(password);
		WebElement signInButton = driver.findElement(By.name("commit"));
		signInButton.click();

		System.out.println("Title is " + driver.getTitle());
	}

	private void createRepo() {
		WebElement newRepoButton = driver.findElement(By.xpath("//a[@class='btn btn-sm btn-primary text-white']")); // locating
																													// button
		newRepoButton.click();

		// fill the form

		WebElement repoNameElement = driver.findElement(By.id("repository_name"));
		repoNameElement.sendKeys("Repo1");

		// selecting radio button for public type
		WebElement radioPublicRepo = driver.findElement(By.xpath("//span[@id='public-description']"));
		radioPublicRepo.click();
		// selecting Add .gitIgnore as Agda
		WebElement gitIgnoreButton = driver.findElement(By.xpath("//summary[@class='btn btn-sm select-menu-button']"));
		gitIgnoreButton.click();

		// selecting item for gitIgnore
		driver.findElement(By.xpath("//span[text()='Agda']")).click();

		// submitting the form
		WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn btn-primary first-in-line']"));
		System.out.println("button captured is " + submitButton.getText());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		submitButton.click();
		String currentURL = driver.getCurrentUrl();
		if (currentURL.contains(repoName)) {
			System.out.println("REPO is created successfully");
		} else
			System.out.println("REPO creation is failed");

	}

	private void browseIssueUrl() {
		// switching to repo
		WebElement repoLink = driver.findElement(By.xpath("//a[@href='/abhinavqa/Repo1']"));
		repoLink.click();

		// reading current url to go to issues page
		String repoURL = driver.getCurrentUrl();
		driver.get(repoURL + "issues");
	}

	public WebElement createIssue() {
		browseIssueUrl();

		WebElement newIssueLink = driver.findElement(By.xpath("//a[@href='/abhinavqa/Repo1/issues/new/choose']"));
		newIssueLink.click();
		String issueTitle = "Issue Title";
		// entering title of issue
		WebElement titleOfIssue = driver.findElement(By.xpath("//input[@name='issue[title]']"));
		titleOfIssue.sendKeys(issueTitle);

		// creating issue
		WebElement submitIssue = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitIssue.click();
		return titleOfIssue;

	}

	public void createAnohterIssue() {

		browseIssueUrl();
		WebElement newIssueLink = driver.findElement(By.xpath("//a[@href='/abhinavqa/Repo1/issues/new/choose']"));
		newIssueLink.click();

		// entering title of issue
		WebElement titleOfIssue = driver.findElement(By.xpath("//input[@name='issue[title]']"));
		titleOfIssue.sendKeys("Issue Title Two");
		WebElement previousIssue = driver.findElement(By.xpath("//a[@href='/abhinavqa/Repo1/issues/1']"));
		// entering description
		WebElement descriptionBox = driver.findElement(By.xpath("//textarea[@id='issue_body']"));

		descriptionBox.sendKeys("Description is with reference to issue " + previousIssue.getText());
		// creating issue
		WebElement submitIssue = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		submitIssue.click();

	}

	public void addingComment() {
		browseIssueUrl();
		driver.findElement(By.xpath("//a[@id='issue_1_link']")).click();
		driver.findElement(By.xpath("//textarea[@id='new_comment_field']")).sendKeys(":+1");
		driver.findElement(By.xpath("//button[@class='btn btn-primary']"));

	}
	public void deleteRepo()
	{
		driver.get("https://github.com/abhinavqa/Repo1/settings");
		driver.findElement(By.xpath("//summary[@class='btn btn-danger boxed-action']")).click();
		driver.findElement(By.xpath("//input[@class='form-control input-block']")).sendKeys("abhinavqa"+"/"+repoName);
		driver.findElement(By.xpath("//button[@class='btn btn-block btn-danger']"));
		
	}
}
