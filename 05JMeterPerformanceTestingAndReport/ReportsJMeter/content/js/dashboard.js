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

    var data = {"OkPercent": 32.690893041047524, "KoPercent": 67.30910695895248};
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
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.06981021038693541, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "Like Post"], "isController": false}, {"data": [0.0, 500, 1500, "Create Post"], "isController": false}, {"data": [0.0, 500, 1500, "Edit Comment"], "isController": false}, {"data": [0.0, 500, 1500, "Edit Post"], "isController": false}, {"data": [0.0, 500, 1500, "Create Comment"], "isController": false}, {"data": [0.0, 500, 1500, "Login"], "isController": false}, {"data": [0.010047846889952153, 500, 1500, "Login-0"], "isController": false}, {"data": [0.44401913875598087, 500, 1500, "Login-1"], "isController": false}, {"data": [0.0, 500, 1500, "Delete Post Tear Down"], "isController": false}, {"data": [0.0, 500, 1500, "Show Comments of a Post"], "isController": false}]}, function(index, item){
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
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 6797, 4575, 67.30910695895248, 22531.089009857318, 4, 159063, 22034.0, 63192.399999999994, 71400.9, 99828.55999999953, 7.9631938065870616, 34.65986039990827, 5.133888756191773], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Like Post", 453, 445, 98.23399558498896, 9031.350993377495, 5, 61019, 10.0, 29323.2, 36956.9, 38914.43999999999, 0.6309244924052291, 0.4926581363319192, 0.30139446020788474], "isController": false}, {"data": ["Create Post", 1102, 1034, 93.82940108892922, 32470.143375680567, 7, 159063, 30020.0, 73861.9, 101997.94999999998, 130874.21000000012, 1.2937552243291726, 0.878699193765673, 0.7340544778664544], "isController": false}, {"data": ["Edit Comment", 318, 317, 99.68553459119497, 761.5723270440251, 5, 30026, 7.0, 15.500000000000114, 128.15000000000003, 30023.43, 0.5329471980049675, 0.41541410196302214, 0.2644310537271109], "isController": false}, {"data": ["Edit Post", 834, 825, 98.92086330935251, 9479.611510791365, 5, 78117, 9.0, 37852.0, 38483.5, 53103.449999999575, 1.1653806490918694, 0.9037930742818377, 0.6921794696679644], "isController": false}, {"data": ["Create Comment", 320, 311, 97.1875, 1758.440625, 5, 40568, 7.0, 87.0, 29829.54999999996, 36519.75000000001, 0.5210911648992991, 0.4473769929701545, 0.28507986739858265], "isController": false}, {"data": ["Login", 1045, 806, 77.12918660287082, 49581.83253588516, 1583, 123590, 45347.0, 92171.8, 93536.2, 95959.98, 1.362435121361027, 17.483278594188594, 1.5533374812094773], "isController": false}, {"data": ["Login-0", 1045, 0, 0.0, 36578.54545454552, 765, 78510, 30090.0, 65492.2, 68698.59999999999, 73328.48, 1.3647767447266725, 0.5575270131109988, 0.9611620820942597], "isController": false}, {"data": ["Login-1", 1045, 206, 19.71291866028708, 13002.832535885163, 4, 59543, 10627.0, 30042.4, 30354.999999999993, 34770.45999999999, 1.364135379666942, 16.947831938521226, 0.5945655848877297], "isController": false}, {"data": ["Delete Post Tear Down", 317, 317, 100.0, 1583.7287066246054, 6, 68067, 9.0, 110.39999999999992, 826.899999999999, 30025.0, 0.5230884993135495, 0.4083438236366697, 0.305061368532349], "isController": false}, {"data": ["Show Comments of a Post", 318, 314, 98.74213836477988, 1358.610062893082, 6, 30027, 7.0, 94.0, 2393.200000000238, 30020.62, 0.5330124084618234, 0.41112399148772477, 0.24213997882197552], "isController": false}]}, function(index, item){
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
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["400", 2444, 53.4207650273224, 35.95703987053112], "isController": false}, {"data": ["500", 1527, 33.377049180327866, 22.465793732529058], "isController": false}, {"data": ["Test failed: text expected to contain /commentId/", 4, 0.08743169398907104, 0.05884949242312785], "isController": false}, {"data": ["Responce body as text should contain &quot;LOGOUT&quot;", 600, 13.114754098360656, 8.827423863469178], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 6797, 4575, "400", 2444, "500", 1527, "Responce body as text should contain &quot;LOGOUT&quot;", 600, "Test failed: text expected to contain /commentId/", 4, "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["Like Post", 453, 445, "400", 434, "500", 11, "", "", "", "", "", ""], "isController": false}, {"data": ["Create Post", 1102, 1034, "500", 1034, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Edit Comment", 318, 317, "400", 310, "500", 7, "", "", "", "", "", ""], "isController": false}, {"data": ["Edit Post", 834, 825, "400", 791, "500", 34, "", "", "", "", "", ""], "isController": false}, {"data": ["Create Comment", 320, 311, "400", 303, "500", 8, "", "", "", "", "", ""], "isController": false}, {"data": ["Login", 1045, 806, "Responce body as text should contain &quot;LOGOUT&quot;", 600, "500", 206, "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["Login-1", 1045, 206, "500", 206, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["Delete Post Tear Down", 317, 317, "400", 303, "500", 14, "", "", "", "", "", ""], "isController": false}, {"data": ["Show Comments of a Post", 318, 314, "400", 303, "500", 7, "Test failed: text expected to contain /commentId/", 4, "", "", "", ""], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
