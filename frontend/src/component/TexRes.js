import React from "react";

class TextRes extends React.Component {
    constructor(props) {
        super(props);
        this.state={
            title:"",
            content:"",
            url:"",
            time:"",
            id:""
        }
        this.handleClick=this.handleClick.bind(this);
    }

    handleClick(){
        var title=this.props.title;
        let username=localStorage.getItem("username");
        let content=this.props.content;
        let id=this.props.id;
        fetch("http://124.70.59.249:8080/addToCollection/"+title+"/"+content+"/"+username+"/"+id) .then(response	=>	response.json())
            .then(data	=>	{
            }).catch(function	(ex)	{
            console.log('parsing	failed',	ex)
        })
    }

    render() {

        return (
            <div className='result-tag'>
                <a href={this.props.url}>{this.props.title}</a>
                <p>{this.props.content}</p>
                <p >{this.props.time}</p>
                <button className="Collection" onClick={this.handleClick}>收藏</button>
            </div>
        );
    }
}
export default TextRes;