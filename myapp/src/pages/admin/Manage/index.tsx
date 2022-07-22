// import { PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns } from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
// import { Button,Space, Tag } from 'antd';
import { useRef } from 'react';
import request from 'umi-request';
import {search} from "@/services/ant-design-pro/api";



// @ts-ignore
const columns: ProColumns<API.CurrentUser>[] = [
  {
    title: 'ID',
    dataIndex: 'userId',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: '昵称',
    dataIndex: 'userName',
    copyable: true,
    ellipsis: true,
  },
  {
    disable: true,
    title: '账号',
    dataIndex: 'userAccount',
    filters: true,
    onFilter: true,
    ellipsis: true,
  },
  {
    title: '头像',
    dataIndex: 'userImage',
    copyable: true,
    ellipsis: true,
    render: (_, record) => (
      <div>
        <img src={record.userImage} width={50} height={50}/>
      </div>
    )
  },
  {
    title: '性别',
    dataIndex: 'userGender',
    valueType: 'select',
    valueEnum: {
      0: {
        text: '女',
        status: "Processing"
      },
      1: {
        text: '男',
        status: 'Success',
      },
    },
  },
  {
    title: '电话',
    dataIndex: 'userPhone',
    ellipsis: true,
  },
  {
    title: '是否有效',
    dataIndex: 'isVaild',
    ellipsis: true,
  },
  {
    disable: true,
    title: '邮箱',
    dataIndex: 'userMail',
    search: false,
    // renderFormItem: (_, { defaultRender }) => {
    //   return defaultRender(_);
    // },
    // render: (_, record) => (
    //   <Space>
    //     {record.labels.map(({ name, color }) => (
    //       <Tag color={color} key={name}>
    //         {name}
    //       </Tag>
    //     ))}
    //   </Space>
    // ),
  },
  {
    title: '创建时间',
    // key: 'showTime',
    dataIndex: 'createTime',
    valueType: 'dateTime',
    sorter: true,
    hideInSearch: true,
  },
  {
    title: '更新时间',
    // key: 'showTime',
    dataIndex: 'updateTime',
    valueType: 'dateTime',
    sorter: true,
    hideInSearch: true,
  },
  {
    title: '权限',
    // key: 'showTime',
    dataIndex: 'userIdentity',
    valueType: 'select',
    valueEnum: {
    0: {
      text: '普通用户',
        status:"Processing"
    },
    1: {
      text: '管理人员',
        status: 'Success',
    },
},

  },
  // {
  //   title: '更新时间',
  //   dataIndex: 'updateTime',
  //   valueType: 'dateRange',
  //   hideInTable: true,
  //   search: {
  //     transform: (value) => {
  //       return {
  //         startTime: value[0],
  //         endTime: value[1],
  //       };
  //     },
  //   },
  // },
  // {
  //   title: '操作',
  //   valueType: 'option',
  //   key: 'option',
  //   render: (text, record, _, action) => [
  //     <a
  //       key="editable"
  //       onClick={() => {
  //         action?.startEditable?.(record.id);
  //       }}
  //     >
  //       编辑
  //     </a>,
  //     <a href={record.url} target="_blank" rel="noopener noreferrer" key="view">
  //       查看
  //     </a>,
  //     <TableDropdown
  //       key="actionGroup"
  //       onSelect={() => action?.reload()}
  //       menus={[
  //         { key: 'copy', name: '复制' },
  //         { key: 'delete', name: '删除' },
  //       ]}
  //     />,
  //   ],
  // },
];


export default () => {
  const actionRef = useRef<ActionType>();
  return (
    <ProTable<API.CurrentUser>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={async (params = {}, sort, filter) => {
        console.log(sort, filter);
        const userList = await search();
        return {
          data: userList
        }
      }}
      editable={{
        type: 'multiple',
      }}
      columnsState={{
        persistenceKey: 'pro-table-singe-demos',
        persistenceType: 'localStorage',
        onChange(value) {
          console.log('value: ', value);
        },
      }}
      rowKey="id"
      search={{
        labelWidth: 'auto',
      }}
      form={{
        // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
        syncToUrl: (values, type) => {
          if (type === 'get') {
            return {
              ...values,
              created_at: [values.startTime, values.endTime],
            };
          }
          return values;
        },
      }}
      pagination={{
        pageSize: 5,
        onChange: (page) => console.log(page),
      }}
      dateFormatter="string"
      headerTitle="高级表格"
    />
  );
};


// valueType: 'select',
//   valueEnum: {
//   all: { text: '超长'.repeat(50) },
//   open: {
//     text: '未解决',
//       status: 'Error',
//   },
//   closed: {
//     text: '已解决',
//       status: 'Success',
//       disabled: true,
//   },
//   processing: {
//     text: '解决中',
//       status: 'Processing',
//   },
// },
