/*
    所有菜单的汇总
*/

// 存储菜单的构造函数
const MenuConstructors = {}

import Bold from './bold'
MenuConstructors.bold = Bold

import Head from './head'
MenuConstructors.head = Head

import FontSize from './fontSize'
MenuConstructors.fontSize = FontSize

import FontName from './fontName'
MenuConstructors.fontName = FontName

import Link from './link'
MenuConstructors.link = Link

import Italic from './italic'
MenuConstructors.italic = Italic

import Redo from './redo'
MenuConstructors.redo = Redo

import StrikeThrough from './strikethrough'
MenuConstructors.strikeThrough = StrikeThrough

import Underline from './underline'
MenuConstructors.underline = Underline

import Undo from './undo'
MenuConstructors.undo = Undo

import List from './list'
MenuConstructors.list = List

import Justify from './justify'
MenuConstructors.justify = Justify

import ForeColor from './foreColor'
MenuConstructors.foreColor = ForeColor

import BackColor from './backColor'
MenuConstructors.backColor = BackColor

import Quote from './quote'
MenuConstructors.quote = Quote

import Code from './code'
MenuConstructors.code = Code

import Emoticon from './emoticon'
MenuConstructors.emoticon = Emoticon

import Table from './table'
MenuConstructors.table = Table

import Video from './video'
MenuConstructors.video = Video

import Image from './img'
MenuConstructors.image = Image

// 吐出所有菜单集合
export default MenuConstructors