### 用户管理部分 ###

1. [用户登录](#user-login)

## 用户管理部分 ##
### <span id="user-login"> 用户登录 </span> ###

通过调用此接口，用户可以登录此系统,Authorization为加密后的代码
公式：Authorization=XOR(Base64(password)) //先用base64加密，然后将加密后的结果用XOR加密

> POST /user/login

	REQUEST:
	POST /user/login

	HEADER:	
	Authorization: <your token> 

	JSON CONTENT:
    {
		"userName":"test",
		"rememberMe": true
    }

	RESPONSE:
    {
       	"at": 1510300144,
	    "code": 200,
	    "result": {
	        "pass": "ok",
			"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJhZG1pbiJ9.wD3k6D3iGmzOsLKIDcU-99JiRMgXkx2xthFGsf9B7XQ"  // jwt放到后端验证 登录成功以后请求其他接口需要在请求头中加上Token，值为这个
	    }
    }

<table>
<tbody>
<tr>
<th> id </th>
<th> 类型 </th>
<th> 备注 </th>
</tr>
<tr>
<td> username </td>
<td> String </td>
<td> 用户名 </td>
</tr>
<tr>
<td> Authorization </td>
<td> String </td>
<td> 定义一个加密算法传输加密后的密码 </td>
</tr>
<tr>
<td> pass </td>
<td> String </td>
<td> 密码验证是否通过，如果是**ok**则通过，否则不通过**err** </td>
</tr>
</tbody>
</table>

