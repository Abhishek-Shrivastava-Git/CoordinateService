 Exercise
Instructions
Our organisation needs a service that given a postcode, it has to retrieve the coordinates (longitude and latitude) for that given postcode. For that purpose
, we should query if we have this information already in the database. If it is not there, then we will call a 3rd party service to get them. 
Finally, this service will have to return a response.

Therefore, we need to build a REST API exposing a unique endpoint that given following input parameters:

POST /coordinates

Body:

name (string)
postcode (string)
Will do the following:

1) Query database table coordinates to retrieve the coordinates for the input postcode.

Hint: You can use an in-memory database like h2.

Model for database:

  Table coordinates:
    - postcode  (primary key)
    - longitude
    - latitude
	


2) If there were no data for the given postcode in the database, then retrieve coordinates for the given postcode calling below endpoint:

GET 

{"status":200,"result":[{"postcode":"MK4 1DL","quality":1,"eastings":485090,"northings":234868,"country":"England","nhs_ha":"South Central","longitude":-0.7618,"latitude":52.005736,"european_electoral_region":"South East","primary_care_trust":"Milton Keynes","region":"South East","lsoa":"Milton Keynes 026A","msoa":"Milton Keynes 026","incode":"1DL","outcode":"MK4","parliamentary_constituency":"Milton Keynes South","admin_district":"Milton Keynes","parish":"Shenley Brook End","admin_county":null,"admin_ward":"Bletchley West","ced":null,"ccg":"NHS Bedfordshire, Luton and Milton Keynes","nuts":"Milton Keynes","codes":{"admin_district":"E06000042","admin_county":"E99999999","admin_ward":"E05009408","parish":"E04012189","parliamentary_constituency":"E14000822","ccg":"E38000249","ccg_id":"M1J4Y","ced":"E99999999","nuts":"TLJ12","lsoa":"E01016754","msoa":"E02003484","lau2":"E06000042"}}]}

- You don't need to deserialise the whole 3rd party API responses, just the required data.
(In this case, just the longitude, latitude.)

Useful information:
api.postcodes.io
I.e:
{"status":200,"result":null}

Body response when postcode found:

    {
      "status": 200,
      "result": [{
        "postcode": "MK4 1DL",
        "quality": 1,
        "eastings": 485090,
        "northings": 234868,
        "country": "England",
        "nhs_ha": "South Central",
        "longitude": -0.7618,
        "latitude": 52.005736,

        ...

I.e:
{"status":200,"result":null} response when postcode NOT found:
{
    "status":200,
    "result":null
}

3) In case, the postcode was retrieved calling the API because it was not present in the database, then you have to save it into the database, so for future calls, it will be present in db.

4) Endpoint response should be the following:

If coordinates have been found either in db or 3rd party API, then return:
Status: 200 Success
Body:
  { "message": "Congratulations {name}. Lat: {latitude}, long: {longitude}" }

Other case:
Status: 404 Not found
Body:
  { "message": "Sorry {name}, we haven't found such postcode." }	


====
my http://api.postcodes.io/postcodes/MK4%201DL	
bb https://api.postcodes.io/postcodes/MK4%201DL	
