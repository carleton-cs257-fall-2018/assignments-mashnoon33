const encodeGetParams = p =>
  Object.entries(p).map(kv => kv.map(encodeURIComponent).join("=")).join("&");

const params = {
  
};

console.log("http://localhost:8080/schools/?" + encodeGetParams(params))
