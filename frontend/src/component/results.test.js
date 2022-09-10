// these imports are something you'd normally configure Jest to import for you
// automatically. Learn more in the setup docs: https://testing-library.com/docs/react-testing-library/setup#cleanup
import '@testing-library/jest-dom'
// NOTE: jest-dom adds handy assertions to Jest and is recommended, but not required

import * as React from 'react'
import {render, fireEvent, screen} from '@testing-library/react'
import SearchResults from "./results";
import MainPage from "../view/main";
import LoginPage from "../view/login";
import renderer from 'react-test-renderer';
import TextRes from "./TexRes";
const res=["事件","内容","https://www.baidu.com","2022-7-12"]
 test('Text result', () => {
   render(<TextRes title={res[0]}
                   content={res[1]}
                   url={res[2]}
                   time={res[3]}
   />)

   expect(screen.getByText(/事件/i)).toBeInTheDocument()
   expect(screen.getByText(/内容/i)).toBeInTheDocument()
     expect(screen.getByText(/2022-7-12/i)).toBeInTheDocument()

 })

//test('Search result change after click', () => {
//  render(<SearchResults />)

//  fireEvent.click(screen.getByText(/时间线/i))

//  expect(screen.getAllByText(/6-23/i)).not.toBeNull()
//  expect(screen.queryByText(/人物/i)).toBeNull()

//  fireEvent.click(screen.getByText(/思维导图/i))

//  expect(screen.getByText(/人物/i)).toBeInTheDocument()
//  expect(screen.queryByText(/6-23/i)).toBeNull()


//})



