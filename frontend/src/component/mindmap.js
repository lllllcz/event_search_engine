import React from "react";
import {Mind} from "tree-graph-react";
import 'tree-graph-react/dist/tree-graph-react.cjs.development.css';


function addChild(data, pid, value, size) {
  const parent = data[pid];
  let keyStr = size.toString();
  if (keyStr.length === 1) {
    keyStr = '00'+keyStr;
  }
  else if (keyStr.length === 2) {
    keyStr = '0' + keyStr;
  }
  const child = {
    _key: keyStr,
    name: value || '',
    father: pid,
    sortList: [],
    checked: false,
    hour: 0,
    limitDay: 0,
  };
  data[keyStr] = child;
  parent.sortList.push(keyStr)

  return data;
}



export default class MindMapRes extends React.Component {
  constructor() {
    super();
    this.state = {
      nodes: [],
    }
  }

  initData = {
    '001': {
      _key: '001',
      name: '事件信息',
      father: '',
      sortList: ['002', '003', '004', '005', '006'],
      contract: false,
      checked: true,
      hour: 0.1,
      limitDay: 1610726400000,
    },
    '002': {
      _key: '002',
      name: '时间：',
      father: '001',
      sortList: [],
      contract: false,
      checked: true,
      hour: 0.1,
      limitDay: 1610726400000,
    },
    '003': {
      _key: '003',
      name: '地点：',
      father: '001',
      sortList: [],
      checked: false,
      hour: 0.1,
      limitDay: 1610726400000,
    },
    '004': {
      _key: '004',
      name: '人物：',
      father: '001',
      sortList: [],
      checked: false,
      hour: 0.1,
      limitDay: 1610726400000,
    },
    '005': {
      _key: '005',
      name: '组织机构：',
      father: '001',
      sortList: [],
      checked: false,
      hour: 0.1,
      limitDay: 1610726400000,
    },
    '006': {
      _key: '006',
      name: '关键词',
      father: '001',
      sortList: [],
      checked: false,
      hour: 0.1,
      limitDay: 1610726400000,
    },
  };

  handleData = (data) => {
    let size = 6;
    let lists = [];
    if (data.address.length > 0 ) {
      lists = data.address;
      lists.forEach((str) => {
        size += 1;
        this.initData = addChild(this.initData, '003', str, size);
      })
    }
    if (data.time.length > 0 ) {
      lists = data.time;
      lists.forEach((str) => {
        size += 1;
        this.initData = addChild(this.initData, '002', str, size);
      })
    }
    if (data.person.length > 0 ) {
      lists = data.person;
      lists.forEach((str) => {
        size += 1;
        this.initData = addChild(this.initData, '004', str, size);
      })
    }
    if (data.org.length > 0 ) {
      lists = data.org;
      lists.forEach((str) => {
        size += 1;
        this.initData = addChild(this.initData, '005', str, size);
      })
    }
    if (data.keyWord.length > 0 ) {
      lists = data.keyWord;
      lists.forEach((str) => {
        size += 1;
        this.initData = addChild(this.initData, '006', str, size);
      })
    }

    this.setState({nodes: this.initData});
  }

  componentDidMount() {
    fetch("http://124.70.59.249:8080/mindMap/"+this.props.target)
      .then(response	=>	response.json())
      .then(data	=>	{
        // console.log(data);
        this.handleData(data);
      })
      .catch(function	(ex) {
        console.log('parsing	failed', ex)
      })
  }

  render() {
    return (
      <Mind nodes={this.state.nodes} startId="001" singleColumn={true} disabled={true} />
    );
  }

}