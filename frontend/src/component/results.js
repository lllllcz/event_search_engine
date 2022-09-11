import React from "react";
import '../css/search_result.css'
import { Mind } from 'tree-graph-react';
import 'tree-graph-react/dist/tree-graph-react.cjs.development.css';
import {Button, Input, Space} from "antd";
import TextRes from "./TexRes";
import bus from './search_bar'
import {keyboard} from "@testing-library/user-event/dist/keyboard";
import {createBrowserHistory} from "history";
import {Link} from "react-router-dom";
import MindMapRes from "./mindmap";


const Res=[["事件","内容","https://www.baidu.com","2022-7-12",1]]
const { Search } = Input;
class SearchResults extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      mind_map: false,
      results: Res,
      isShow:true,
      target:''
    }
  }



  handleChangeDisplayToMind = () => {
    this.setState({mind_map: true});
  }

  handleChangeDisplayToTime = () => {
    this.setState({mind_map: false});
  }

  handleCollection=()=>{
    const history = createBrowserHistory();
    history.push(
        {
          pathname:"/CollectionView",
        }  );
    history.go();
    return;
  }

  handleHistory=()=>{
    const history = createBrowserHistory();
    history.push(
        {
          pathname:"/HistoryView",
        }  );
    history.go();
    return;
  }



  onSearch = (value) => {
    this.setState({
      isShow:false,
      target:value
    })
    let username=localStorage.getItem("username");
    var time=new Date();
    fetch("http://124.70.59.249:8080/search/" + value + "/" + username + "/" + time).then(response => response.json())
      .then(data => {
        this.setState(
          {
            isShow: true,
            results: data
          }
        )
      }).catch(function (ex) {
      console.log('parsing	failed', ex)
    })
  };

  handleLogout = () => {
    console.log("handle logout");
    fetch("http://124.70.59.249:8080/logout", {credentials:'include'})
      .then(response	=> response.json())
      .then(data => {console.log(data)})
      .catch(function	(ex) {console.log('parsing failed',	ex)})
  }


  render() {
    let elements = [];
    if (this.state.mind_map) {
      elements.push(<MindMapRes target={this.state.target} />)
    }
    else {
      elements.push(this.state.results.map(function (res, x) {
        return (
          <div key={x}>
            <TextRes title={res[0]}
                     content={res[1]}
                     url={res[2]}
                     time={res[3]}
                     id={res[4]}
            />
          </div>
        )
      }, this))
    }

    return (
      <div>
        <div className='search-bar'>
          <Space direction="vertical">
            <h1 className='search-bar-title'>事件搜索引擎</h1>
            <Link to="/" onClick={this.handleLogout} >退出登录</Link>
            <Search
              placeholder="请输入事件名"
              allowClear
              enterButton
              size="large"
              style={{width: '600px'}}
              onSearch={this.onSearch}
            />
          </Space>
        </div>
        <div>
          {
            this.state.isShow ? (
                <div>
                  <h1 className='result-title'>查询结果</h1>
                  <Button onClick={this.handleChangeDisplayToMind} style={{marginRight: '10px'}}>思维导图</Button>
                  <Button onClick={this.handleChangeDisplayToTime} style={{marginRight: '10px'}}>时间线</Button>
                  <Button onClick={this.handleCollection}>收藏夹</Button>
                  <Button onClick={this.handleHistory}>历史记录</Button>
                  {elements}
                </div>) :
              (<div>正在搜索……</div>)
          }
        </div>
      </div>
    );
  }

}

export default SearchResults;