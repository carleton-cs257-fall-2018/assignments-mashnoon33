var params = {
  adm_rate: [0,.35],
  sat_avg: [1,1300],
  md_earn_wne_p10: [0, 70000],
  region_id: "None",
  actcmmid: [1,28],
  costt4_a: [0, 45000],
  degree: 3,
  owner: 2
}

var p = "positive"
var owner = ["#pub", "#priv", "#all"]
$("#pub").on("click", function() {
if  ($("#pub").hasClass(p)) {
  $("#pub").toggleClass("positive")
}
else {
  for (item of owner) {
    if  ($(item).hasClass(p)) {
      $(item).toggleClass("positive")
    }
  }
  $("#pub").toggleClass("positive")
  params.owner=0
}

});

$("#priv").on("click", function() {
  if  ($("#priv").hasClass(p)) {
    $("#priv").toggleClass("positive")
  }
  else {
    for (item of owner) {
      if  ($(item).hasClass(p)) {
        $(item).toggleClass("positive")
      }
    }
    $("#priv").toggleClass("positive")
    params.owner=2

}
});

$("#all").on("click", function() {
  if  ($("#all").hasClass(p)) {
    $("#all").toggleClass("positive")
  }
  else {
    for (item of owner) {
      if  ($(item).hasClass(p)) {
        $(item).toggleClass("positive")
      }
    }
    $("#all").toggleClass("positive")
    params.owner="None"
}
  });

degree= ["#ass","#bac", "#grad"]


  $("#ass").on("click", function() {
    if  ($("#ass").hasClass(p)) {
      $("#ass").toggleClass("positive")
    }
    else {
      for (item of degree) {
        if  ($(item).hasClass(p)) {
          $(item).toggleClass("positive")
        }
      }
      $("#ass").toggleClass("positive")
      params.degree=2

  }
  });

  $("#bac").on("click", function() {
    if  ($("#bac").hasClass(p)) {
      $("#bac").toggleClass("positive")
    }
    else {
      for (item of degree) {
        if  ($(item).hasClass(p)) {
          $(item).toggleClass("positive")
        }
      }
      $("#bac").toggleClass("positive")
      params.degree=3

  }
  });


  $("#grad").on("click", function() {
    if  ($("#grad").hasClass(p)) {
      $("#grad").toggleClass("positive")
    }
    else {
      for (item of degree) {
        if  ($(item).hasClass(p)) {
          $(item).toggleClass("positive")
        }
      }
      $("#grad").toggleClass("positive")
      params.degree=4

  }
  });


$("#search").on("click", function() {
  $("#mytable > tbody").html("");
  var prompt = $('#prompt').val()
  if (prompt!= "") {
    loadJsons_Name(prompt);
  }
  else {
    loadJsons();
  }
});



const encodeGetParams = p =>
  Object.entries(p).map(kv => kv.map(encodeURIComponent).join("=")).join("&");



$('.ui.sidebar').sidebar({
    context: $('.bottom.segment')
  })
  // .sidebar('hide')
// window.addEventListener('load', async e => {
//     await loadJsons();
// });

function dynamicEvent() {
  $('.ui.modal')
  .modal({
    centered: false
  })
  .modal('show')

  var queryURL = 'http://perlman.mathcs.carleton.edu:' + api_port + '/school/?opeid=' + this.id
  $.getJSON(queryURL, function(data) {
      var logo = document.getElementById("logo");
      while (logo.firstChild) {
          logo.removeChild(logo.firstChild);
      }
      var img = document.createElement('img');
      img.src = "https://logo.clearbit.com/"+ data["insturl"].toString().replace("www.", "")
      logo.appendChild(img);

      $("#name").text(data["name"])
      $("#location").text(data["city"] + ", " + data["state"])
      $("#adm_rate").text(Math.round(data["adm_rate"]*100) + "% Acceptance Rate")



    });
}




var sel = $('.ui.dropdown.second')
sel.dropdown({
    apiSettings: {
        url: 'http://perlman.mathcs.carleton.edu:' + api_port + '/regions/',
    },
    onChange: function(value, text) {
      console.log(value);
        if (value!=10) {
        params.region_id=value
        }
        else {
          params.region_id="None"
        }
      }
});

var table = document.getElementById("results");
async function loadJsons() {


$.getJSON("http://perlman.mathcs.carleton.edu:" + api_port + "/schools/?"+  encodeGetParams(params), function(data) {

    for (item of data) {
      var row = table.insertRow(table.rows.length);
      var x= row.insertCell(0);
      var img = document.createElement('img');
      img.src = "https://logo.clearbit.com/"+ item["insturl"].toString().replace("www.", "") + "?size=20"
      x.appendChild(img);
      row.insertCell(1).innerHTML = item["name"]
      row.insertCell(2).innerHTML = item["city"]+ ", " +item["state"]
      row.insertCell(3).innerHTML = Math.round(item["adm_rate"]*100) + "%"
      row.insertCell(4).innerHTML = item["sat_avg"]
      row.insertCell(5).innerHTML = item["actcmmid"]
      row.insertCell(6).innerHTML = item["costt4_a"]
      row.insertCell(7).innerHTML = item["md_earn_wne_p10"]
      var y= row.insertCell(8);
      var but = document.createElement('button');
      but.className = 'ui button'
      but.innerHTML = 'view'
      but.id = item["opeid"]
      y.appendChild(but);
      but.onclick = dynamicEvent;

    }






   });


}

async function loadJsons_Name(name) {


$.getJSON("http://perlman.mathcs.carleton.edu:" + api_port + "/schools/name/"+  name, function(data) {

    for (item of data) {
      var row = table.insertRow(table.rows.length);
      var x= row.insertCell(0);
      var img = document.createElement('img');
      img.src = "https://logo.clearbit.com/"+ item["insturl"].toString().replace("www.", "") + "?size=20"
      x.appendChild(img);
      row.insertCell(1).innerHTML = item["name"]
      row.insertCell(2).innerHTML = item["city"]+ ", " +item["state"]
      row.insertCell(3).innerHTML = Math.round(item["adm_rate"]*100) + "%"
      row.insertCell(4).innerHTML = item["sat_avg"]
      row.insertCell(5).innerHTML = item["actcmmid"]
      row.insertCell(6).innerHTML = item["costt4_a"]
      row.insertCell(7).innerHTML = item["md_earn_wne_p10"]
      var y= row.insertCell(8);
      var but = document.createElement('button');
      but.className = 'ui button'
      but.innerHTML = 'view'
      but.id = item["opeid"]
      y.appendChild(but);
      but.onclick = dynamicEvent;

    }






   });


}


$('#sat').range({
  min: 0,
  max: 1600,
  start: 1300,
  step: 10,

  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#sat_score').html(secVal + " - " + firstVal);
  },

  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#sat_score').html(secVal + " - " + firstVal);
    params.sat_avg= [secVal, firstVal]
  }
});


$('#act').range({
  min: 0,
  max: 36,
  start: 28,
  step: 1,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#act_score').html(secVal + " - " + firstVal);
    params.actcmmid= [secVal, firstVal]

  }
});

$('#cost').range({
  min: 0,
  max: 100000,
  start: 45000,
  step: 1000,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#cost_n').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#cost_n').html(secVal + " - " + firstVal);
    params.costt4_a= [secVal, firstVal]

  }
});

$('#salary').range({
  min: 0,
  max: 200000,
  start: 70000,
  step: 1000,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#salary_n').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#salary_n').html(secVal + " - " + firstVal);
    params.md_earn_wne_p10= [secVal, firstVal]

  }
});

$('#selective').range({
  min: 0,
  max: 100,
  start: 35,
  step: 1,
  function() {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#selective_n').html(secVal + " - " + firstVal);
  },
  onChange: function(value) {
    $self = $(this),
    firstVal = $self.range('get thumb value'),
    secVal = $self.range('get thumb value', 'second');
    $('#selective_n').html(secVal + " - " + firstVal);
    params.adm_rate= [secVal/100, firstVal/100]

  }
});
