# Server and Database Commands: Google Scholar Data Integrator

## 1. Project Purpose
The main goal of this project is to automate the process of integrating academic data for the university's Top 3 researchers. It aims to replace a manual, error-prone workflow with a reliable and efficient automated solution, ensuring that the institutional research database is always up-to-date with the latest metrics from Google Scholar.

## 2. Key Functionalities
The application is a back-end Java program that performs the following key functions in a structured sequence:

*   **Data Extraction:** Connects to the Google Scholar API (via SerpApi) to fetch researcher and publication data.
*   **Data Parsing:** Parses the incoming JSON response from the API.
*   **Data Mapping:** Maps the parsed data into a structured data model within the Java application, following the MVC design pattern.
*   **Data Persistence:** Connects to a MySQL database and inserts the structured data into the appropriate tables.

## 3. Project Relevance
This project solves a critical operational problem for the university's Innovation Center: the significant time and effort lost to manual data entry and the high risk of data inconsistencies in the research database.

By automating this workflow, the project facilitates:
*   **Timeliness:** Reports can be generated quickly and on-demand.
*   **Accuracy:** Eliminates human error in data transcription.
*   **Efficiency:** Frees up staff (like Sandra) to focus on data analysis rather than data entry.

---
*This repository contains all code, documentation, and deliverables for the "Server and Database Commands" challenge. in the file "API_Technical_Report.md"*