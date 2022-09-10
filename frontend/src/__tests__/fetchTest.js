import {fetchData,fetchData1,fetchData2,fetchData3} from "../component/fetchData";

test('fetchData 返回结果为 success', () =>{
        return fetchData().then((response) => {
            expect(response.data).toEqual("success")
        });
    });

test('fetchData1 返回结果为 failed', () =>{
    return fetchData1().then((response) => {
        expect(response.data).toEqual("failed")
    });
});

test('fetchData2 返回结果为 用户名已存在！', () =>{
    return fetchData2().then((response) => {
        expect(response.data).toEqual("用户名已存在！")
    });
});

test('fetchData3 返回结果为 注册成功！', () =>{
    return fetchData3().then((response) => {
        expect(response.data).toEqual("注册成功！")
    });
});