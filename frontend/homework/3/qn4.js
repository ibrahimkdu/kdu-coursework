let input='{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';
let jsondata=JSON.parse(input);
for(const key in jsondata)
{
    if(key!="email" && typeof jsondata[key] === 'string')
    {
        jsondata[key]=jsondata[key].toUpperCase();
    }
}
console.log(jsondata);
// jsondata.firstName=jsondata.firstName.toUpperCase();
// jsondata.lastName=jsondata.lastName.toUpperCase();
// jsondata.city=jsondata.city.toUpperCase();
// jsondata.country=jsondata.country.toUpperCase();
delete jsondata.email;
const result = JSON.stringify(jsondata); 
console.log(result);

