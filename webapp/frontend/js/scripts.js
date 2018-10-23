$('.ui.sidebar').sidebar({
    context: $('.bottom.segment')
  })
  // .sidebar('hide')
window.addEventListener('load', async e => {
    await loadJsons();


});

var sel = $('.ui.dropdown.second')

async function loadJsons() {
sel.dropdown({
    apiSettings: {
        url: 'states.json',

    },
    filterRemoteData: true,
    onChange: function(value, text, $selectedItem) {
        $("#down").removeClass('disabled').addClass('positive');
        $("#down").html('Generate Calendar');
        que = value.split(',');
    }
});
}


$(document).ready(function() {
	$('#my-range').range({
		min: 0,
		max: 1600,
		start: 1200,
    onChange: function(value) {
      $('#display-3').html(value);
    }
	});
});


// async function loadJsons() {
//     console.log('JSON loading!');
//
//     $.getJSON("data/Classes.json", function(data) {
//         JSONItems = data;
//     });
//     sel.states({
//         apiSettings: {
//             url: 'data/Classes.json',
//
//         },
//         filterRemoteData: true,
//         onChange: function(value, text, $selectedItem) {
//             $("#down").removeClass('disabled').addClass('positive');
//             $("#down").html('Generate Calendar');
//             que = value.split(',');
//         }
//     });
// }
//
//
//
// function class2ICS(className) {
//     var classes = JSONItems.results;
//
//     for (var i = 0; i < classes.length; i++) {
//         if (classes[i].name === className) {
//             parseClass(classes[i]);
//             return;
//         }
//     }
// }
//
//
// function shitFuckWeHaveClassEveryDay(item) {
//     if (item.M != null && item.T != null && item.W != null && item.TH != null && item.F != null) {
//         checkForMWF(item);
//         checkForTTH(item);
//         return true;
//     }
//     return false;
// }
//
// $(document).ready(function() {
//     $('body').find('img[src$="https://cdn.rawgit.com/000webhost/logo/e9bd13f7/footer-powered-by-000webhost-white2.png"]').remove();
// });
//
// function checkForMWF(item) {
//     if (item.M === item.W && item.M != null) {
//         rrule.byday = ['MO', 'WE'];
//         rrule.until = termEND;
//
//         var parts = item.M.split("-");
//         var stime = parts[0].trim();
//         var etime = parts[1].trim();
//
//         start = termSTART + " " + stime;
//         end = termSTART + " " + etime;
//
//         cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//
//         if (item.F != null) {
//             rrule.byday = ['FR'];
//             rrule.until = termEND;
//             var parts = item.F.split("-");
//             var stime = parts[0].trim();
//             var etime = parts[1].trim();
//
//             var startF = termSTART.replace("10", "14");
//
//             start = startF + " " + stime;
//             end = startF + " " + etime;
//
//             cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//         }
//
//         item.cat = "mwf";
//         checkSingles(item);
//
//
//
//         // TODO: CHECK SINGLES (ex: GEO 110.52)
//         labcheck(item);
//
//         return true;
//     }
//
//     return false;
// }
//
// function checkForTTH(item) {
//     if (item.T === item.TH && item.T != null) {
//         rrule.until = termEND;
//         rrule.byday = ['TU', 'TH']
//
//         var parts = item.T.split("-");
//         var stime = parts[0].trim();
//         var etime = parts[1].trim();
//
//         var startT = termSTART.replace("10", "11");
//
//
//         start = startT + " " + stime;
//         end = startT + " " + etime;
//
//         cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//         labcheck(item);
//
//         item.cat = "tth";
//         checkSingles(item);
//
//
//         return true;
//     }
//     return false;
// }
//
// function checkSingles(item) {
//     console.log("SINGLES" + item.cat);
//
//     for (var key in item) {
//         if (item[key] != null && (item[key].includes("am") | item[key].includes("pm")) && item[key].includes("-") && item[key].includes(":") && key != "text") {
//             //   // console.log(item.name + " " + key + " : " + item[key]);
//             //   console.log(key);
//             // }
//
//
//             console.log(key + " : " + item[key]);
//             if (item.cat === "tth" || item.cat === null) {
//                 console.log("mwf ran");
//                 if (key === "M") {
//                     rrule.until = termEND;
//                     rrule.byday = ['MO']
//
//                     var parts = item.M.split("-");
//                     var stime = parts[0].trim();
//                     var etime = parts[1].trim();
//
//                     var startT = termSTART;
//
//
//                     start = startT + " " + stime;
//                     end = startT + " " + etime;
//
//                     cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 }
//
//
//                 if (key === "W") {
//                     rrule.until = termEND;
//                     rrule.byday = ['WE']
//
//                     var parts = item.W.split("-");
//                     var stime = parts[0].trim();
//                     var etime = parts[1].trim();
//
//                     var startT = termSTART.replace("10", "12");
//
//
//                     start = startT + " " + stime;
//                     end = startT + " " + etime;
//
//                     cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 }
//
//
//
//                 if (key === "F") {
//                     rrule.until = termEND;
//                     rrule.byday = ['FR']
//
//                     var parts = item.F.split("-");
//                     var stime = parts[0].trim();
//                     var etime = parts[1].trim();
//
//                     var startT = termSTART.replace("10", "13");
//
//
//                     start = startT + " " + stime;
//                     end = startT + " " + etime;
//
//                     cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 }
//
//             }
//
//
//             if (item.cat === "mwf" || item.cat === null) {
//                 console.log("tth ran");
//
//
//                 if (key === "T") {
//                     console.log("TU WORKED");
//                     rrule.until = termEND;
//                     rrule.byday = ['TU']
//
//                     var parts = item.T.split("-");
//                     var stime = parts[0].trim();
//                     var etime = parts[1].trim();
//
//                     var startT = termSTART.replace("10", "11");
//
//
//                     start = startT + " " + stime;
//                     end = startT + " " + etime;
//
//                     cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 }
//                 if (key === "TH") {
//                     console.log("TH WORKED");
//                     rrule.until = termEND;
//                     rrule.byday = ['TH']
//
//                     var parts = item.TH.split("-");
//                     var stime = parts[0].trim();
//                     var etime = parts[1].trim();
//
//                     var startT = termSTART.replace("10", "13");
//
//
//                     start = startT + " " + stime;
//                     end = startT + " " + etime;
//
//                     cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 }
//
//                 console.log("or nothing?");
//             }
//
//
//             labcheck(item);
//
//
//
//             // TODO: Add LAB days
//
//         }
//
//     }
//     return false;
// }
//
// function labcheck(item) {
//
//     for (var key in item) {
//         if (item[key] != null && key.includes("_")) {
//             if (key === "M_Lab") {
//                 rrule.until = termEND;
//                 rrule.byday = ['MO']
//
//                 var parts = item.M_Lab.split("-");
//                 var stime = parts[0].trim();
//                 var etime = parts[1].trim();
//
//                 var startT = termSTART;
//
//
//                 start = startT + " " + stime;
//                 end = startT + " " + etime;
//
//                 cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 return true;
//             }
//             console.log("Got so far");
//
//             if (key === "T_Lab") {
//                 rrule.until = termEND;
//                 rrule.byday = ['TU']
//                 console.log("Bingo");
//
//                 var parts = item.T_Lab.split("-");
//                 var stime = parts[0].trim();
//                 var etime = parts[1].trim();
//
//                 var startT = termSTART.replace("10", "11");
//
//
//                 start = startT + " " + stime;
//                 end = startT + " " + etime;
//
//                 cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 return true;
//             }
//
//             if (key === "W_Lab") {
//                 rrule.until = termEND;
//                 rrule.byday = ['WE']
//
//                 var parts = item.W_Lab.split("-");
//                 var stime = parts[0].trim();
//                 var etime = parts[1].trim();
//
//                 var startT = termSTART.replace("10", "12");
//
//
//                 start = startT + " " + stime;
//                 end = startT + " " + etime;
//
//                 cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 return true;
//             }
//
//             if (key === "TH_Lab") {
//                 rrule.until = termEND;
//                 rrule.byday = ['TH']
//
//                 var parts = item.TH_Lab.split("-");
//                 var stime = parts[0].trim();
//                 var etime = parts[1].trim();
//
//                 var startT = termSTART.replace("10", "13");
//
//
//                 start = startT + " " + stime;
//                 end = startT + " " + etime;
//
//                 cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 return true;
//             }
//
//             if (key === "F_Lab") {
//                 rrule.until = termEND;
//                 rrule.byday = ['FR']
//
//                 var parts = item.F_Lab.split("-");
//                 var stime = parts[0].trim();
//                 var etime = parts[1].trim();
//
//                 var startT = termSTART.replace("10", "13");
//
//
//                 start = startT + " " + stime;
//                 end = startT + " " + etime;
//
//                 cal.addEvent(item.name, item.text, item.location, start, end, rrule);
//                 return true;
//             }
//             return false
//         }
//
//     }
// }
