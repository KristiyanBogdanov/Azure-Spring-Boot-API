<h1 align="center">
  Azure Spring Boot Api
</h1>

### Contents:
  - [Overview](#overview)
  - [Database](#database)
  - [API endpoints](#api-endpoints)
  - [Deployment](#deployment)

## Overview
The Azure Spring Boot API application is developed as a comprehensive Health Tracker System. This system is designed to facilitate the tracking and management of health-related data. Leveraging the power of Azure services, the application ensures scalability and high availability to meet the demands of large-scale usage. The system utilizes MySQL as its primary database, providing a robust and reliable foundation for data storage and retrieval. Furthermore, OpenAPI documentation is integrated, enabling developers to easily understand and interact with the API endpoints, facilitating seamless integration with client applications.

## Database
The Health Tracker System, powered by MySQL as its database management system, effectively stores and organizes health-related data in six tables: users, profiles, sleep-stats, meals-stats, meals, and workouts. Each table serves a distinct purpose, ensuring the management of essential information. In addition to the primary tables, the system incorporates audit tables exclusively for the "profiles" and "users" tables. These audit tables serve as a reliable mechanism for tracking and logging any modifications or changes made to the data within these tables. By capturing and retaining a comprehensive history of alterations, the system promotes transparency and accountability, enhancing data governance and compliance measures. The relationships between the tables are depicted in the following diagram:

<img alt="image" src="https://github.com/KristiyanBogdanov/Azure-Spring-Boot-API/blob/main/documentation/db-relationships.png">
