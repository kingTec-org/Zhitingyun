package com.example.zhitingyun.mvp;


import com.example.zhitingyun.model.Confirm;
import com.example.zhitingyun.model.EquipmentParamater;
import com.example.zhitingyun.model.HomePage;
import com.example.zhitingyun.model.News;
import com.example.zhitingyun.model.NewsDetail;
import com.example.zhitingyun.model.Order;
import com.example.zhitingyun.model.OrderDetail;
import com.example.zhitingyun.model.ProfessorInfo;
import com.example.zhitingyun.model.Quick;
import com.example.zhitingyun.model.SchedualItem;

import org.w3c.dom.ls.LSInput;

import java.util.List;

import coder.mylibrary.base.BaseView;

/**
 * Created by dasiy on 2017/12/13.
 */

public interface ViewsContainer {
    interface LoginView extends BaseView {
        void login(ProfessorInfo professorInfo);

    }

    interface ForgetPasswordView extends BaseView {
        void verifyCodeSend();

        void verifyCode();


    }

    interface ResetPasswordView extends BaseView {
        void resetPassword();


    }

    interface UserInfoView extends BaseView {
        void getInfo(ProfessorInfo professorInfo);

    }

    interface MySchedualView extends BaseView {
        void addSchedule();

        void getScheduleList(List<SchedualItem> list);

        void deleteSchedule();

        void repeatSchedule();

    }

    interface ConfirmView extends BaseView {
        void getOrderList(List<Confirm> list);
    }

    interface ConfirmListView extends BaseView {
        void getOrderList(List<Confirm> list);

        void getQuickOrderList(List<Confirm> list);
    }

    interface PersonInfoView extends BaseView {
        void getInfo(ProfessorInfo professorInfo);

    }

    interface TodoView extends BaseView {
        void homePage(HomePage homePage);

    }

    interface NewsListView extends BaseView {
        void getNewsList(List<News> list);

        void readNews(NewsDetail newsDetail);

        void readAll();

    }

    interface WorkspaceView extends BaseView {
        void haveNewMsg(Integer integer);


    }

    interface ChangePhoneView extends BaseView {
        void sendVerifyCode();

        void changePhone();


    }

    interface ChangePasswordView extends BaseView {
        void changePassword();


    }

    interface FeedbackView extends BaseView {
        void addFeedback();


    }

    interface NewsDetailView extends BaseView {
        void readNews(NewsDetail newsDetail);


    }


    interface OrderDetailView extends BaseView {
        void getOrder(OrderDetail orderDetail);

        void receiveOrder(OrderDetail orderDetail);

        void finishCure();

        void recordCure(OrderDetail orderDetail);

        void startCure(OrderDetail orderDetail);

        void cancelOrder();

        void getParamaterList(List<EquipmentParamater> list);


    }

    interface OrderTreattingView extends BaseView {
        void getParamaterList(List<EquipmentParamater> list);

    }

    interface OrderHistoryView extends BaseView {
        void getOrder(OrderDetail orderDetail);
    }


    interface MainView extends BaseView {

        void finishCure();

    }

    interface QuickView extends BaseView {
        void getRequestList(List<Quick> list);

        void receiveRequest(OrderDetail orderDetail);
    }

    interface OrderRefuseView extends BaseView {
        void refuseOrder();

    }

    interface AccountManageView extends BaseView {
        void logout();

        void getInfo(ProfessorInfo professorInfo);

    }

    interface WriteReportView extends BaseView {
        void submitParamater(EquipmentParamater equipmentParamater);

        void getOrder(OrderDetail orderDetail);

        void cureReport();


    }


//
//    interface PhoneBindView extends BaseView {
//
//        void verifyCode();
//
//        void bindPhone(PhoneBindRequest phoneBindRequest);
//
//    }
//
//    interface InformationDetailView extends BaseView {
//        void getInformationById(InformationRequest informationRequest);
//
//    }
//
//    interface MakeProductOrderView extends BaseView {
//        void getAddressList(AddressList addressList);
//
//        void addOrUpdateShoppingCart(Object object);
//
//        void getTemplateList(TemplateRequest templateRequest);
//
//        void addOrder(IdRequest idRequest);
//
//        void alipay(AlipayRequest alipayRequest);
//
//        void checkDeliveryFree(DeliveryRequest deliveryRequest);
//    }
//
//
//    interface AddressListView extends BaseView {
//        void getAddressList(AddressList addressList);
//
//        void deleteAddress(Object object);
//    }
//
//
//    interface AddressAddView extends BaseView {
//        void addOrUpdateAddress(Object object);
//    }
//
//    interface StayOrderReturnView extends BaseView {
//        void getStayOrderReturnListByUser(StayOrderReturnListRequest stayOrderReturnListRequest);
//
//        void cancelStayOrderReturn();
//
//        void updateStayOrderReturn();
//    }
//
//    interface GuidMainView extends BaseView {
//        void getPoiList(PoiListRequest poiListRequest);
//
//        void getRouteList(RouteListRequest routeListRequest);
//    }
//
//
//    interface LoginView extends BaseView {
//        void login(ProfessorInfo loginRequest);
//
//        void loginByWeibo(ProfessorInfo loginRequest);
//
//        void getLoginBg(CarouselRequest carouselRequest);
//
//        void weixinLogin(ProfessorInfo loginRequest);
//
//    }
//
//    interface ForgetView extends BaseView {
//        void getLoginBg(CarouselRequest carouselRequest);
//
//        void verifyCode();
//
//        void modifyPasswordUser();
//    }
//
//    interface TicketDetailView extends BaseView {
//        void getTicketById(TicketDetailRequest ticketDetailRequest);
//
//    }
//
//    interface TicketInfoView extends BaseView {
//        void getTicketResourceById(TicketResourceRequest ticketResourceRequest);
//
//    }
//
//    interface StayDetailView extends BaseView {
//        void getStayInfo(StayInfoRequest stayInfoRequest);
//
//        void getNestestPoi(PoiRequest poiRequest);
//
//    }
//
//    interface MakeStayOrderView extends BaseView {
//        void addStayOrder(IdRequest idRequest);
//
//        void alipay(AlipayRequest alipayRequest);
//
//        void getCustomPhone(CustomRequest customRequest);
//    }
//
//    interface PersonInfoView extends BaseView {
//        void getUserInfo(UserRequest userRequest);
//
//        void getCustomPhone(CustomRequest customRequest);
//    }
//
//    interface PersonInfoDetailView extends BaseView {
//        void getUserInfo(UserRequest userRequest);
//
//        void updateUserInfo();
//
//        void uploadFile(Resource resource);
//
//    }
//
//    interface SetMainView extends BaseView {
//        void getUserInfo(UserRequest userRequest);
//
//        void logout();
//
//        void weixinBind();
//
//        void weiboBind();
//    }
//
//    interface OpenMainView extends BaseView {
//        void getBackgroundList(CarouselRequest carouselRequest);
//    }
//
//    interface PoiDetailView extends BaseView {
//        void getPoiById(PoiRequest poiRequest);
//    }
//
//
//    interface MakeTicketOrderView extends BaseView {
//        void addTicketOrder(IdRequest idRequest);
//
//        void alipay(AlipayRequest alipayRequest);
//
//        void getTicketResourceById(TicketResourceRequest ticketResourceRequest);
//
//    }
//
//    interface GiftView extends BaseView {
//        void getUserInfo(UserRequest userRequest);
//
//        void getCarousel(CarouselRequest carouselRequest);
//
//        void getProductTypeList(ProductTypeRequest productTypeRequest);
//
//        void getProductList(ProductRequest productRequest);
//    }
//
//    interface TicketView extends BaseView {
//        void getUserInfo(UserRequest userRequest);
//
//        void getCarousel(CarouselRequest carouselRequest);
//
//        void getTicketListByUser(TicketRequest ticketRequest);
//
//        void setTicketSaledList(TicketRequest ticketRequest);
//
//        void getTicketById(TicketDetailRequest ticketDetailRequest);
//    }
//
//    interface StayView extends BaseView {
//        void getStayList(StayRequest stayRequest);
//    }
//
//    interface StayMapView extends BaseView {
//        void getStayList(StayRequest stayRequest);
//    }
//
//    interface TicketListView extends BaseView {
//        void getTicketListByUser(TicketRequest ticketRequest);
//    }
//
//    interface ProductListView extends BaseView {
//        void getProductList(ProductRequest productRequest);
//    }
//
//    interface HomeView extends BaseView {
//        void getNestestPoiFromMe(PoiRequest poiRequest);
//
//        void getUserInfo(UserRequest userRequest);
//
//
//        void getProductRecommendList(ProductRequest productRequest);
//
//        void getCarousel(CarouselRequest carouselRequest);
//
//        void getInformationList(InformationListRequest informationListRequest);
//    }
//
//    interface ProductOrderView extends BaseView {
//        void getProductOrderListByUser(ProductOrderListRequest productOrderListRequest);
//
//        void updateProductOrder();
//
//        void alipay(AlipayRequest alipayRequest);
//
//    }
//
//    interface StayOrderView extends BaseView {
//        void getStayOrderListByUser(StayOrderListRequest stayOrderListRequest);
//
//        void updateStayOrder();
//
//        void alipay(AlipayRequest alipayRequest);
//
//        void createStayOrderReturn(Object object);
//
//    }
//
//    interface StayOrderDetailView extends BaseView {
//        void getStayOrderById(StayOrderRequest stayOrderRequest);
//
//        void updateStayOrder();
//
//        void alipay(AlipayRequest alipayRequest);
//
//        void createStayOrderReturn(Object object);
//
//    }
//
//    interface ReturnStayOrderDetailView extends BaseView {
//        void getStayOrderReturnById(StayOrderReturnRequest stayOrderReturnRequest);
//
//        void cancelStayOrderReturn();
//
//
//        void updateStayOrderReturn();
//
//    }
//
//    interface TicketOrderDetailView extends BaseView {
//        void getTicketOrderById(TicketOrderRequest ticketOrderRequest);
//
//        void setTicketOrderStatus();
//
//        void alipay(AlipayRequest alipayRequest);
//
//        void addTicketOrderReturn(Object object);
//
//        void getTicketResourceById(TicketResourceRequest ticketResourceRequest);
//
//    }
//
//    interface ReturnTicketOrderDetailView extends BaseView {
//        void getTicketOrderReturnById(TicketOrderReturnRequest ticketOrderReturnRequest);
//
//        void setTicketOrderReturnStatus();
//
//        void cancelTicketOrderReturn();
//    }
//
//    interface TicketOrderView extends BaseView {
//        void getTicketOrderByUser(TicketOrderListRequest ticketOrderListRequest);
//
//        void setTicketOrderStatus();
//
//        void alipay(AlipayRequest alipayRequest);
//
//        void addTicketOrderReturn(Object object);
//
//    }
//
//    interface TicketOrderReturnView extends BaseView {
//        void getTicketOrderReturnByUser(TicketOrderListRequest ticketOrderListRequest);
//
//        void cancelTicketOrderReturn();
//
//        void setTicketOrderReturnStatus();
//    }
//
//    interface ProductOrderReturnView extends BaseView {
//        void getProductOrderReturnListByUser(ProductOrderReturnRequest productOrderReturnRequest);
//
//        void updateProductReturnOrder();
//
//        void cancelProductReturnOrder();
//
//    }
//
//    interface StayOrderLogView extends BaseView {
//        void getLogByOrderId(StayOrderLogRequest stayOrderLogRequest);
//    }
//
//    interface RequestReturnView extends BaseView {
//        void uploadFile(Resource resource);
//
//        void createOrderReturn(Object object);
//
//    }
//
//    interface EvaluateMainView extends BaseView {
//        void uploadFile(Resource resource);
//
//        void addEvaluations();
//
//        void getProductOrderById(ProductOrderRequest productOrderRequest);
//
//
//    }
//
//    interface ProductOrderDetailView extends BaseView {
//        void getProductOrderById(ProductOrderRequest productOrderRequest);
//
//        void updateProductOrder();
//
//        void alipay(AlipayRequest alipayRequest);
//
//    }
//
//    interface ReturnProductOrderDetailView extends BaseView {
//        void getProductOrderReturnById(ProductOrderReturnDetail productOrderReturnDetail);
//
//        void updateProductReturnOrder();
//
//        void cancelProductReturnOrder();
//
//    }
//
//
//    interface ProductDetailView extends BaseView {
//        void getProductInfo(ProductRequest0 productRequest);
//
//        void addOrUpdateShoppingCart(Object object);
//
//        void getEvaluationListByProductId(ResponseData<EvaluationRequest> requestResponseData);
//    }
//
//    interface TextView extends BaseView {
//        void getProductInfo(ProductRequest0 productRequest);
//    }
//
//    interface EvaluationListView extends BaseView {
//        void getEvaluationListByProductId(ResponseData<EvaluationRequest> requestResponseData);
//
//    }
//
//    interface ShopCartView extends BaseView {
//        void getShoppingCart(CartRequest cartRequest);
//
//        void shoppingDelete(Object object);
//
//        void addOrUpdateShoppingCart(Object object);
//    }


}
