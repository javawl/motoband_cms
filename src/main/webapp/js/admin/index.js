var loading = $('#fountainTextG');
window.onload = function ()
{
	$.ajax (
	{
	    type : "POST",
	    url : "../admin/privilege",
	    success : function (data)
	    {
		    var json = eval (data);
		    
		    for (i in json)
		    {
			    
			    var key = json[i].privilege_key;
			    var _index = key.indexOf ("_");
			    var head = key.substring (0, _index);
			    $ ("#" + head).css ("display", "block");
		    }
	    }
	});
}
function userlist (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function boxlist (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
}

function conflist (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function rolelist (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function carlist (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function dataversionlist (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function configdata (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function clientupdate (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&ctype=1");
}
function motouser (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function motonews (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function motorecommend (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function motorecommend_new(url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function motorecommend_heavy(url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function newscategory(url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function banner (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function newbanner(url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function ach (url, adminGuid)
{
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}

function storyrecommend(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function userrecommend(url,adminGuid){
	$ ("#page-wrapper").load ("../motouser/b" + "?userGuid=" + adminGuid);
}

function imgmanage ()
{
	$ ("#page-wrapper").load ("../boxmanage/imgSourceManage");
}
function selOwnMessage (user_guid)
{
	$ ("#page-wrapper").load ("../usermanage/selOwnMessage?userGuid=" + user_guid);
}
function payorder (url,adminGuid)
{
	$ ("#page-wrapper").load (url +"?userGuid=" +adminGuid+"&page=1&limit=20&order=0");
}
function payorderlog (url,adminGuid)
{
	$ ("#page-wrapper").load (url +"?userGuid=" +adminGuid+"&page=1&limit=20&order=0");
}
function insuranceupdate(url,adminGuid)
{
	$ ("#page-wrapper").load (url +"?userGuid=" +adminGuid+"&page=1&limit=20&order=0");
}
function ad(url,adminGuid)
{
	$ ("#page-wrapper").load (url +"?userGuid=" +adminGuid+"&page=1&limit=20&order=0");
}
function brandparentlist(url,adminGuid)
{
	$ ("#page-wrapper").load (url +"?userGuid=" +adminGuid+"&page=1&limit=20&order=0");
}
function motobandgp(url,adminGuid)
{
	$ ("#page-wrapper").load (url +"?userGuid=" +adminGuid+"&page=1&limit=20&order=0&orderConditions=starttime");
}
function useroperation(url,adminGuid){
	$ ("#page-wrapper").load (url+"?userGuid=" + adminGuid);
}
function userolddriver(url,adminGuid){
	$ ("#page-wrapper").load (url+"?userGuid=" + adminGuid);
}
function userboxapprove(url,adminGuid){
	$ ("#page-wrapper").load (url+ "?userGuid=" + adminGuid);
}
function activity(url,adminGuid){
	$ ("#page-wrapper").load (url+ "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function motoschoolpackage(url,adminGuid){
	$ ("#page-wrapper").load (url+ "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function motoschoolbox(url,adminGuid){
	$ ("#page-wrapper").load (url+ "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function motoschoolvideo(url,adminGuid){
	$ ("#page-wrapper").load (url+ "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function reportlog(url,adminGuid){
	$ ("#page-wrapper").load (url+ "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}

function newsrecommendcategory(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function mall(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function mallparenttype(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function malltype(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function mallbrand(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function malllabel(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function mallstyle(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function mallnotify(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function mallproductrecommend(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function dataStatistics(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&type=1");
}

function topicAndDiscuss(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&topictype=0");
}
function businessapplylist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&state=-1");
}
function businessuserinfoapplylist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function businessuserlist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function usecarmainlist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&groupid=-1");
}
function giftexchangelist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function businesscommentlist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid);
}
function businessredirctshoplist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}

function businessredirctshopservicelist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&buid=0");
}

function businessdataStatistics(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&buserid=-1");
}
function motoboxnews(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function secondcarlist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function adpool(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&adtype=0");
}
function advertising(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=1&adtype=0");
}
function triballist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&tribaltype=0");
}
function secondcarmainzonelist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function consignmentlist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function openscreenlist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function openqijilist(url,adminGuid){
	loading.show();
	$ ("#page-wrapper").text("正在加载中.....");
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
	loading.hide();
}
function mallbase(url,adminGuid,grouptype){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&groupid=-1&grouptype="+grouptype);
}
function equippinggroup(url,adminGuid,grouptype){
	console.log("abc");
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&grouptype="+grouptype);
}
function newmotomodellist(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function sendSystemMessage(url,adminGuid){
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
}
function openbotuserlist(url,adminGuid){
	loading.show();
	$ ("#page-wrapper").text("正在加载中.....");
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
	loading.hide();
}

function opendevicelog(url,adminGuid){
	loading.show();
	$ ("#page-wrapper").text("正在加载中.....");
	$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
	loading.hide();
}

