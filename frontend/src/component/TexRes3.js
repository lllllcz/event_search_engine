import React from "react";

class TextRes3 extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            theme:"",
            time:""
        }
        this.handleClick=this.handleClick.bind(this);
    }

    handleClick(){
        var theme=this.props.theme;
        let username=localStorage.getItem("username");
        let time=this.props.time;
        fetch("http://124.70.59.249:8080/deleteHistory/"+theme+"/"+username+"/"+time) .then(response	=>	response.json())
            .then(data	=>	{
            }).catch(function	(ex)	{
            console.log('parsing	failed',	ex)
        })
    }

    render() {

        return (
            <div className='result-tag'>
                <p >{this.props.theme}</p>
                <p>{this.props.time}</p>
                <button className="Collection" onClick={this.handleClick}>删除记录</button>
            </div>
        );
    }
}
export default TextRes3;