function showSection(sectionId) {
    const sections = document.querySelectorAll("section");
    sections.forEach(section => section.classList.add("hidden"));
    document.getElementById(sectionId).classList.remove("hidden");
}

async function fetchData(apiUrl) {
    try {
        // const response = await fetch(apiUrl);
        const response = await fetch(apiUrl);
        return await response.json();
    } catch (error) {
        console.error("Error fetching data:", error);
    }
}

function initChart(ctx, type, data, options) {
    return new Chart(ctx, { type, data, options });
}

function showSection(sectionId) {
    const sections = document.querySelectorAll("section");
    sections.forEach(section => section.classList.add("hidden"));
    document.getElementById(sectionId).classList.remove("hidden");
}
async function loadTopics() {
    // 获取数据
    const response = await fetchData('http://localhost:9111/api/stackoverflow/get/topNTopics');
    console.log(response);
    if (response.code === 200 && response.data) {
        // 解析数据
        const labels = response.data.map(item => item.name); // 提取 name 作为 X 轴
        const dataValues = response.data.map(item => item.frequency); // 提取 frequency 作为 Y 轴
        console.log(labels);
        console.log(dataValues);

        // 初始化柱状图
        const ctx = document.getElementById('topicsChart').getContext('2d');
        console.log(ctx);
        // initChart(ctx, 'bar', labels, dataValues);
        initChart(ctx, 'bar', {
            labels: labels,
            datasets: [{
                label: '热门主题',
                data: dataValues,
                backgroundColor: 'rgba(75, 192, 192, 0.6)',
            }]
        });
    } else {
        console.error("Failed to load topics data:", response.message);
    }
}

async function loadEngagement() {
    const data = await fetchData('http://localhost:9111/api/stackoverflow/get/topNUserEngageTopics');
    console.log(data);
    if (data.code === 200 && data.data) {
        // 解析数据
        const labels = data.data.map(item => item.name); // 提取 name 作为 X 轴
        const dataValues = data.data.map(item => item.frequency); // 提取 frequency 作为 Y 轴
        console.log(labels);
        console.log(dataValues);

        // 初始化柱状图
        const ctx = document.getElementById('engagementChart').getContext('2d');
        console.log(ctx);
        // initChart(ctx, 'bar', labels, dataValues);
        initChart(ctx, 'bar', {
            labels: labels,
            datasets: [{
                label: '用户参与度',
                data: dataValues,
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
            }]
        });
    } else {
        console.error("Failed to load topics data:", response.message);
    }
}

async function loadErrors() {
    const data = await fetchData('http://localhost:9111/api/stackoverflow/get/topNErrors');
    console.log(data);
    if (data.code === 200 && data.data) {
        // 解析数据
        const labels = data.data.map(item => item.name); // 提取 name 作为 X 轴
        const dataValues = data.data.map(item => item.frequency); // 提取 frequency 作为 Y 轴
        console.log(labels);
        console.log(dataValues);

        // 初始化柱状图
        const ctx = document.getElementById('errorsChart').getContext('2d');
        console.log(ctx);
        // initChart(ctx, 'bar', labels, dataValues);
        initChart(ctx, 'bar', {
            labels: labels,
            datasets: [{
                label: '常见错误',
                data: dataValues,
                borderColor: 'rgba(255, 99, 132, 1)',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
            }]
        });
    } else {
        console.error("Failed to load topics data:", data.message);
    }
}

async function loadQuality() {
    const data = await fetchData('http://localhost:9111/api/stackoverflow/get/answerQuality');
    console.log(data);
    if (data.code === 200 && data.data) {
        // const ctx = document.getElementById('qualityChart').getContext('2d');
        // initBarChart(ctx, data);

        // const highQualityAvgTimeDiff = data.data.map(item => item.highQualityAvgTimeDiff);
        // const lowQualityAvgTimeDiff = data.data.map(item => item.lowQualityAvgTimeDiff);
        // const highQualityAvgAcceptRate = data.data.map(item => item.highQualityAvgAcceptRate);
        // const lowQualityAvgAcceptRate = data.data.map(item => item.lowQualityAvgAcceptRate);
        // const highQualityAvgReputation = data.data.map(item => item.highQualityAvgReputation);
        // const lowQualityAvgReputation = data.data.map(item => item.lowQualityAvgReputation);

        const ctxTimeDiff = document.getElementById('AvgTimeDiffChart').getContext('2d');
        const ctxAcceptRate = document.getElementById('AvgAcceptRateChart').getContext('2d');
        const ctxReputation = document.getElementById('AvgReputationChart').getContext('2d');

        initDoubleChart(ctxTimeDiff, 'Avg Time Diff', data.data.highQualityAvgTimeDiff, data.data.lowQualityAvgTimeDiff);
        initDoubleChart(ctxAcceptRate, 'Avg Accept Rate', data.data.highQualityAvgAcceptRate, data.data.lowQualityAvgAcceptRate);
        initDoubleChart(ctxReputation, 'Avg Reputation', data.data.highQualityAvgReputation, data.data.lowQualityAvgReputation);

        // initChart(ctx1, 'bar', {
        //     labels: '时间差/min',
        //     datasets: [{
        //         label: '回答平均时间差',
        //         data: {
        //             labels: ["quality"],
        //             datasets: [{
        //                 label: 'High Quality',
        //                 data: [highQualityAvgTimeDiff], // 高质量数据
        //                 borderColor: 'rgba(54, 162, 235, 1)',
        //                 backgroundColor: 'rgba(54, 162, 235, 0.2)',
        //             }, {
        //                 label: 'Low Quality',
        //                 data: [lowQualityAvgTimeDiff], // 低质量数据
        //                 borderColor: 'rgba(255, 99, 132, 1)',
        //                 backgroundColor: 'rgba(255, 99, 132, 0.2)',
        //             }]
        //         },
        //         borderColor: 'rgba(255, 99, 132, 1)',
        //         backgroundColor: 'rgba(255, 99, 132, 0.2)',
        //     }]
        // });
        //
        // initChart(ctx2, 'bar', {
        //     labels: '平均接受率/%',
        //     datasets: [{
        //         label: '回答人平均接受率',
        //         data: {
        //             labels: ["quality"],
        //             datasets: [{
        //                 label: 'High Quality',
        //                 data: [highQualityAvgAcceptRate], // 高质量数据
        //                 borderColor: 'rgba(54, 162, 235, 1)',
        //                 backgroundColor: 'rgba(54, 162, 235, 0.2)',
        //             }, {
        //                 label: 'Low Quality',
        //                 data: [lowQualityAvgAcceptRate], // 低质量数据
        //                 borderColor: 'rgba(255, 99, 132, 1)',
        //                 backgroundColor: 'rgba(255, 99, 132, 0.2)',
        //             }]
        //         },
        //         borderColor: 'rgba(255, 99, 132, 1)',
        //         backgroundColor: 'rgba(255, 99, 132, 0.2)',
        //     }]
        // });
        //
        // initChart(ctx3, 'bar', {
        //     labels: '平均信誉分',
        //     datasets: [{
        //         label: '回答人平均信誉分',
        //         data: {
        //             labels: ["quality"],
        //             datasets: [{
        //                 label: 'High Quality',
        //                 data: [highQualityAvgReputation], // 高质量数据
        //                 borderColor: 'rgba(54, 162, 235, 1)',
        //                 backgroundColor: 'rgba(54, 162, 235, 0.2)',
        //             }, {
        //                 label: 'Low Quality',
        //                 data: [lowQualityAvgReputation], // 低质量数据
        //                 borderColor: 'rgba(255, 99, 132, 1)',
        //                 backgroundColor: 'rgba(255, 99, 132, 0.2)',
        //             }]
        //         },
        //         borderColor: 'rgba(255, 99, 132, 1)',
        //         backgroundColor: 'rgba(255, 99, 132, 0.2)',
        //     }]
        // });

    } else {
        console.error("Failed to load topics data:", data.message);
    }
}
async function loadErrorFrequency() {
    // 获取用户输入
    const errorName = document.getElementById('errorInput').value.trim();

    if (!errorName) {
        alert("请输入一个错误名！");
        return;
    }

    // 拼接请求 URL
    const apiUrl = `http://localhost:9111/api/stackoverflow/get/errorFrequency?error=${encodeURIComponent(errorName)}`;

    // 请求数据
    const response = await fetchData(apiUrl);

    if (response.code === 200 && response.data) {
        // 解析数据
        document.getElementById('errorFrequencyDisplay').textContent = response.data;
    } else {
        console.error("Failed to load error frequency data:", response.message);
        alert("查询失败，请检查输入或稍后再试！");
    }
}
async function loadTopicFrequency() {
    // 获取用户输入
    const topicName = document.getElementById('topicInput').value.trim();

    if (!topicName) {
        alert("请输入一个错误名！");
        return;
    }

    // 拼接请求 URL
    const apiUrl = `http://localhost:9111/api/stackoverflow/get/topicFrequency?topic=${encodeURIComponent(topicName)}`;

    // 请求数据
    const response = await fetchData(apiUrl);

    if (response.code === 200 && response.data) {
        document.getElementById('topicFrequencyDisplay').textContent = response.data;
    } else {
        console.error("Failed to load topic frequency data:", response.message);
        alert("查询失败，请检查输入或稍后再试！");
    }
}

function initDoubleChart(ctx, label, highQualityValue, lowQualityValue) {
    // 创建柱状图
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [label], // 每个图表只有一个标签
            datasets: [{
                label: 'High Quality',
                data: [highQualityValue], // 高质量数据
                borderColor: 'rgba(54, 162, 235, 1)',
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
            }, {
                label: 'Low Quality',
                data: [lowQualityValue], // 低质量数据
                borderColor: 'rgba(255, 99, 132, 1)',
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

// function initBarChart(ctx, treeData) {
//     // 构建柱状图的数据
//     const labels = ['Avg Time Diff', 'Avg Accept Rate', 'Avg Reputation'];
//     const highQualityData = {
//         label: 'High Quality',
//         data: [
//             treeData.data.highQualityAvgTimeDiff,
//             treeData.data.highQualityAvgAcceptRate,
//             treeData.data.highQualityAvgReputation
//         ],
//         backgroundColor: 'rgba(54, 162, 235, 0.2)',
//         borderColor: 'rgba(54, 162, 235, 1)',
//         borderWidth: 1
//     };
//
//     const lowQualityData = {
//         label: 'Low Quality',
//         data: [
//             treeData.data.lowQualityAvgTimeDiff,
//             treeData.data.lowQualityAvgAcceptRate,
//             treeData.data.lowQualityAvgReputation
//         ],
//         backgroundColor: 'rgba(255, 99, 132, 0.2)',
//         borderColor: 'rgba(255, 99, 132, 1)',
//         borderWidth: 1
//     };
//
//     // 创建柱状图
//     new Chart(ctx, {
//         type: 'bar',
//         data: {
//             labels: labels,
//             datasets: [highQualityData, lowQualityData]
//         },
//         options: {
//             scales: {
//                 y: {
//                     beginAtZero: true
//                 }
//             }
//         }
//     });
// }

document.addEventListener('DOMContentLoaded', () => {
    loadTopics();
    loadEngagement();
    loadErrors();
    loadQuality();
});