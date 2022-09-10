import React from "react";
import SearchBar from "../component/search_bar";
import SearchResults from "../component/results";
import MyHeader from "../component/web_top";
import TexRes2 from "../component/TexRes2";
import TextRes from "../component/TexRes";
import TextRes2 from "../component/TexRes2";
import {Link} from "react-router-dom";

const Res=[["事件","内容","https://www.baidu.com","2022-7-12"]]
class CollectionView extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            results:Res

        }
    }
    componentDidMount() {
        let username=localStorage.getItem("username");
        fetch("http://124.70.59.249:8080/showCollection/"+username) .then(response	=>	response.json())
            .then(data	=>	{
                this.setState(
                    {
                        results:data
                    }
                )
            }).catch(function	(ex)	{
            console.log('parsing	failed',	ex)
        })
    }


    render() {
        return (
            <div>
                <MyHeader />
                <div className="container">
                    <h1 className='search-bar-title'>收藏夹</h1>
                    <Link to="/MainPage">返回</Link>
                    { this.state.results.map(function(res,x){
                        return (
                            <div key={x}>
                                <TextRes2 title={res[0]}
                                         content={res[1]}
                                         url={res[2]}
                                         time={res[3]}
                                />
                            </div>
                        )
                    },this)
                    }
                </div>
            </div>
        );
    }


}

export default CollectionView;