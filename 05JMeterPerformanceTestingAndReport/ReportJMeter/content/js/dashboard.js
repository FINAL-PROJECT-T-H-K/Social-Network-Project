/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 47.83683559950556, "KoPercent": 52.16316440049444};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.11722290894107952, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.003048780487804878, 500, 1500, "Create Post"], "isController": false}, {"data": [8.928571428571428E-4, 500, 1500, "Register Random User"], "isController": false}, {"data": [0.003745318352059925, 500, 1500, "Delete Post"], "isController": false}, {"data": [0.09551886792452831, 500, 1500, "Login"], "isController": false}, {"data": [0.23113207547169812, 500, 1500, "Login-0"], "isController": false}, {"data": [0.3384433962264151, 500, 1500, "Login-1"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 2427, 1266, 52.16316440049444, 17204.72435105069, 5, 86967, 13550.0, 33817.40000000002, 48105.2, 67210.12, 3.499720253098846, 26.277578254328866, 2.4216252984027027], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Create Post", 328, 239, 72.86585365853658, 29146.10365853659, 10, 83523, 30016.0, 64675.60000000002, 68896.15000000001, 79361.93, 0.4744354507426072, 0.33012527021485566, 0.2691865203920457], "isController": false}, {"data": ["Register Random User", 560, 559, 99.82142857142857, 18023.935714285704, 409, 30953, 20681.5, 30201.9, 30240.8, 30460.399999999998, 0.8355976537611594, 0.5794233821299981, 0.5524410269495165], "isController": false}, {"data": ["Delete Post", 267, 260, 97.37827715355806, 7956.303370786517, 8, 59858, 11.0, 35109.80000000001, 40154.19999999999, 54186.2, 0.3871686228107572, 0.29542055738506023, 0.22595456958667212], "isController": false}, {"data": ["Login", 424, 149, 35.14150943396226, 23559.42216981132, 759, 86967, 29319.5, 48933.0, 55789.5, 63000.5, 0.6328178753139837, 12.98679793735327, 0.7217699936494338], "isController": false}, {"data": ["Login-0", 424, 0, 0.0, 14156.478773584884, 240, 60133, 9818.5, 30038.5, 30043.75, 41840.0, 0.6333131688810223, 0.256253664142376, 0.448764087110434], "isController": false}, {"data": ["Login-1", 424, 59, 13.915094339622641, 9402.511792452835, 5, 30801, 2869.0, 30021.0, 30029.0, 30272.0, 0.6331835993500907, 12.738102152096227, 0.27351485125412167], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["400", 196, 15.481832543443918, 8.0758137618459], "isController": false}, {"data": ["500", 565, 44.62875197472354, 23.279769262463947], "isController": false}, {"data": ["Responce body as text should contain &quot;LOGOUT&quot;", 90, 7.109004739336493, 3.708281829419036], "isController": false}, {"data": ["409", 415, 32.78041074249605, 17.099299546765554], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 2427, 1266, "500", 565, "409", 415, "400", 196, "Responce body as text should contain &quot;LOGOUT&quot;", 90, "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Create Post", 328, 239, "500", 239, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Register Random User", 560, 559, "409", 415, "500", 144, "", "", "", "", "", ""], "isController": false}, {"data": ["Delete Post", 267, 260, "400", 196, "500", 64, "", "", "", "", "", ""], "isController": false}, {"data": ["Login", 424, 149, "Responce body as text should contain &quot;LOGOUT&quot;", 90, "500", 59, "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["Login-1", 424, 59, "500", 59, "", "", "", "", "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
