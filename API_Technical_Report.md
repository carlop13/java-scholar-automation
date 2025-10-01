# Technical Report: Google Scholar API via SerpApi

**Project:** Server and Database Commands - Automation of Researcher Information
**Prepared by:** Carlos Guadalupe López Trejo
**Date:** 30/09/2025

## 1. Introduction
This report summarizes the technical specifications of the SerpApi Google Scholar API, which will be used to automate the extraction of publication data for the university's Top 3 researchers. The following sections detail the necessary information to proceed with the development of the Java client in Sprint 2.

---

### 2. Endpoints

SerpApi utilizes a single, unified endpoint for all its search engine queries. The specific search engine to use is specified as a parameter in the request, not through a different URL.

- **Primary Endpoint:** `https://serpapi.com/search`
- **Method:** `GET`
- **Description:** This is the only URL needed to perform searches. All customization is handled through query parameters.

---

### 3. Authentication Methods

Access to the API is controlled via a private API Key. Every request sent to the endpoint must include this key for authentication.

- **Method:** API Key Parameter
- **Process to Obtain:**
    1. Create a free account on the [SerpApi website](https://serpapi.com/).
    2. Navigate to the account dashboard after logging in.
    3. The private API Key will be displayed there.
- **Usage:** The key must be included in every request as a query parameter named `api_key`.
    - **Example:** `https://serpapi.com/search?api_key=YOUR_PRIVATE_API_KEY&...`

**Note:** The API Key must be kept confidential and should not be exposed in client-side code or public repositories.

---

### 4. Query Parameters

The following are the most relevant query parameters for this project to search for authors and their publications:

| Parameter | Description | Example |
| :--- | :--- | :--- |
| **`engine`** | **(Required)** Specifies which Google service to use. For this project, it must always be `google_scholar`. | `engine=google_scholar` |
| **`author_id`** | The unique ID of a Google Scholar author profile. **This is the most effective parameter for targeting our Top 3 researchers.** | `author_id=z_vS-Y0AAAAJ` |
| **`hl`** | The language to use for the search. `en` for English is recommended for consistency. | `hl=en` |
| **`num`** | The number of results to return per page. The default is 20. | `num=10` |
| **`start`** | The result offset for pagination. `0` is the first page, `20` is the second, etc. | `start=20` |

---

### 5. Response Formats

The API returns data in **JSON** format by default. The structure is well-defined and contains several key objects. For our project, the most important sections are `organic_results` (for publications) and `author_results` (for author details).

- **Main JSON Structure:**
    - `search_information`: Contains metadata about the search query's success and timing.
    - `author_results`: Contains details about the researcher (name, citations, h-index).
    - `organic_results`: An array where each element is a JSON object representing a single publication.

- **Structure of a single `organic_results` item:**
    ```json
    {
      "title": "A survey of deep learning for scientific discovery",
      "publication_info": {
        "summary": "...",
        "authors": [
          { "name": "A Author" }
        ]
      },
      "cited_by": {
        "total": 178
      }
    }
    ```
    - **Key Fields for Mapping:** `title`, `publication_info.summary`, `publication_info.authors`, and `cited_by.total`.

---

### 6. Usage Limits

SerpApi operates on a freemium model. The limits are based on the subscribed plan.

- **Free Plan:**
    - **100 successful searches per month.**
    - This is sufficient for development, testing, and small-scale execution for this project.
- **Paid Plans:**
    - For production use or more frequent updates, a paid plan would be necessary. These plans increase the number of searches per month significantly.

---

### 7. Code Examples

The following is a basic example of how to make a request to the API using Java with a standard HTTP library.

**Java (using `java.net.http.HttpClient`):**
```java
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScholarApiClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        String authorId = "z_vS-Y0AAAAJ"; // Example
        String apiKey = "YOUR_PRIVATE_API_KEY";

        // Construct the request URL
        String url = String.format(
            "https://serpapi.com/search.json?engine=google_scholar_author&author_id=%s&api_key=%s",
            authorId, apiKey
        );

        // Create an HTTP client and request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response status and body
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}