

# Calendria

Calendria is holiday viewer app use for view holidays filtered by country location and year. The app developed for Mobile Application Development module final assesment.
  
## Functionalities
* Set default location by accessing device location.
  + We access the device location and show the holidays.
* Set default location by accessing device location.
  + We access the date from the device.
* We have displayed the holidays by filtering by it's months.
* Display a calendar for the current month.
* We also have used specific colors to denote the type of holiday.
* There will be a notification sent to the user about upcoming holiday.

## Tech Stack 

#### Material Components for Android

```http
  com.google.android.material:material:1.5.0
  ```
#### Volley 

```http
  com.android.volley:volley:1.2.1
  ```

## API Reference

#### Get all items

```http
  GET https://calendarific.com/api/v2/holidays?&api_key=${api_key}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get Countries

```http
  GET https://calendarific.com/api/v2/countries
```





#### Get Holidays by country and year

```http
  GET https://calendarific.com/api/v2/holidays?&api_key=${api_key}&country=${country}&year=${year}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `api_key`      | `string` | **Required**. Id of item to fetch |
| `country`      | `string` | **Required**. Id of item to fetch |
| `id`      | `string` | **Required**. Id of item to fetch |

## Color Reference

| Color             | Hex                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Background color | ![#E2E2E2](https://via.placeholder.com/10/E2E2E2?text=+) #E2E2E2 |
| Primary text color | ![#000000](https://via.placeholder.com/10/000000?text=+) #000000 |


## Contributing

We have used a sub brach to commit our commits and the main branch was commited by the group leader Dinith.

Salman Rizwan  -  COHDSE221f016
\
Amashi Silva   -  COHDSE221f002
\
Daneej Ahnaf   -  COHDSE221f037
\
Dinith Perera  -  COHDSE221f002



